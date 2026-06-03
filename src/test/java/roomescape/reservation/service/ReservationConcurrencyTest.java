package roomescape.reservation.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import roomescape.reservationtime.domain.ReservationTime;
import roomescape.test_config.MutableClock;
import roomescape.test_config.TestClockConfig;
import roomescape.test_config.fixture.SQLFixtureGenerator;
import roomescape.theme.domain.Theme;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = "spring.docker.compose.skip.in-tests=false")
@Import({TestClockConfig.class, SQLFixtureGenerator.class})
@Sql(value = "/acceptance-cleanup.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class ReservationConcurrencyTest {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private SQLFixtureGenerator sqlFixtureGenerator;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private MutableClock clock;

    @Test
    @DisplayName("동시에 같은 날짜, 시간, 테마로 예약하면 확정 예약은 하나만 생성되어야 한다.")
    void create_concurrently_sameSlot_onlyOneConfirmed() throws Exception {
        // given
        clock.setFixed(LocalDate.of(2025, 5, 10));

        ReservationTime time = sqlFixtureGenerator.insertReservationTime(LocalTime.of(10, 0));
        Theme theme = sqlFixtureGenerator.insertTheme("레벨2 탈출", "우테코 레벨2를 탈출하는 내용입니다.", "https://example.com/theme.png");
        LocalDate date = LocalDate.of(2025, 5, 11);

        // when
        executeConcurrently(
                () -> reservationService.create("브라운", date, time.getId(), theme.getId()),
                () -> reservationService.create("포비", date, time.getId(), theme.getId())
        );

        // then
        long confirmedCount = countConfirmedReservations(date, time.getId(), theme.getId());
        assertThat(confirmedCount).isEqualTo(1);
    }

    private void executeConcurrently(Runnable first, Runnable second) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        CountDownLatch startLatch = new CountDownLatch(1);

        try {
            Future<?> firstFuture = executor.submit(() -> {
                await(startLatch);
                first.run();
            });
            Future<?> secondFuture = executor.submit(() -> {
                await(startLatch);
                second.run();
            });

            startLatch.countDown();
            firstFuture.get(3, TimeUnit.SECONDS);
            secondFuture.get(3, TimeUnit.SECONDS);
        } finally {
            executor.shutdownNow();
        }
    }

    private void await(CountDownLatch latch) {
        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException(e);
        }
    }

    private long countConfirmedReservations(LocalDate date, Long timeId, Long themeId) {
        Long count = jdbcTemplate.queryForObject("""
                        SELECT COUNT(*)
                        FROM reservation
                        WHERE date = :date
                          AND time_id = :timeId
                          AND theme_id = :themeId
                          AND status = 'CONFIRMED'
                        """,
                new MapSqlParameterSource()
                        .addValue("date", Date.valueOf(date))
                        .addValue("timeId", timeId)
                        .addValue("themeId", themeId),
                Long.class);

        return count == null ? 0 : count;
    }
}
