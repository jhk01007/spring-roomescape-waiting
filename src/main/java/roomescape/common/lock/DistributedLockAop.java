package roomescape.common.lock;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import roomescape.common.exception.DomainException;

import java.lang.reflect.Method;

import static roomescape.reservation.exception.ReservationErrorCode.*;

@Aspect
@Component
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DistributedLockAop {

    private static final String REDISSON_LOCK_PREFIX = "LOCK:";

    private final RedissonClient redissonClient;

    @Around("@annotation(roomescape.common.lock.DistributedLock)")
    public Object lock(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        DistributedLock distributedLock = method.getAnnotation(DistributedLock.class);

        String key = getKey(signature.getParameterNames(), joinPoint.getArgs(), distributedLock.key());
        RLock rLock = redissonClient.getLock(key);

        try {
            if(!rLock.tryLock(distributedLock.waitTime(), distributedLock.timeUnit())) {
                throw new DomainException(ACQUIRE_LOCK_FAIL);
            }
            return joinPoint.proceed();
        } finally {
            if (rLock.isHeldByCurrentThread()) {
                rLock.unlock();
            }
        }
    }

    private static String getKey(String[] parameterNames, Object[] args, String key) {
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();

        for (int i = 0; i < parameterNames.length; i++) {
            context.setVariable(parameterNames[i], args[i]);
        }

        return REDISSON_LOCK_PREFIX +
                parser.parseExpression(key).getValue(context, Object.class);
    }
}
