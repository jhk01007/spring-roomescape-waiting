package roomescape.test_config;

import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import roomescape.reservation.repository.JdbcReservationRepository;
import roomescape.reservationtime.repository.JdbcReservationTimeRepository;
import roomescape.test_config.fixture.SQLFixtureGenerator;
import roomescape.theme.repository.JdbcThemeRepository;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@JdbcTest
@Import({
        JdbcReservationRepository.class,
        JdbcReservationTimeRepository.class,
        JdbcThemeRepository.class,
        SQLFixtureGenerator.class
})
public @interface RepositoryTest {
}
