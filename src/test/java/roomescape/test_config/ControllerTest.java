package roomescape.test_config;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import roomescape.reservation.service.ReservationService;
import roomescape.reservationtime.service.ReservationTimeService;
import roomescape.theme.service.ThemeService;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@WebMvcTest
@MockitoBean(types = {
        ReservationService.class,
        ReservationTimeService.class,
        ThemeService.class
})
public @interface ControllerTest {
}
