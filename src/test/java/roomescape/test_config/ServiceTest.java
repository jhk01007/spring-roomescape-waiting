package roomescape.test_config;

import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import roomescape.reservation.repository.JdbcReservationRepository;
import roomescape.reservation.service.ReservationService;
import roomescape.reservation.service.validator.ReservationValidator;
import roomescape.reservationtime.repository.JdbcReservationTimeRepository;
import roomescape.reservationtime.service.ReservationTimeService;
import roomescape.test_config.fixture.SQLFixtureGenerator;
import roomescape.theme.repository.JdbcThemeRepository;
import roomescape.theme.service.ThemeService;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@JdbcTest
@Import({
        TestClockConfig.class,
        ReservationService.class,
        ReservationTimeService.class,
        ThemeService.class,
        JdbcReservationRepository.class,
        JdbcReservationTimeRepository.class,
        JdbcThemeRepository.class,
        ReservationValidator.class,
        SQLFixtureGenerator.class
})
public @interface ServiceTest {
}
