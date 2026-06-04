package roomescape.reservation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import roomescape.common.lock.DistributedLock;
import roomescape.reservation.domain.Reservation;
import roomescape.reservation.domain.Status;
import roomescape.reservation.repository.ReservationRepository;
import roomescape.reservation.service.validator.ReservationValidator;
import roomescape.reservationtime.domain.ReservationTime;
import roomescape.theme.domain.Theme;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static roomescape.reservation.domain.Status.CONFIRMED;

@Service
@RequiredArgsConstructor
public class ReservationCreator {

    private final ReservationValidator reservationValidator;
    private final ReservationRepository reservationRepository;
    private final Clock clock;

    @Transactional
    @DistributedLock(key = "'key:' + #date + ':' + #time.getId + ':' + #theme.getId")
    public Reservation saveReservation(String guestName, LocalDate date, ReservationTime time, Theme theme) {
        Status status = determineState(date, time.getId(), theme.getId());
        Reservation reservation = Reservation.create(guestName, date, time, theme, status, LocalDateTime.now(clock));
        reservationValidator.validateCreate(reservation);
        return reservationRepository.save(reservation);
    }

    private Status determineState(LocalDate date, Long timeId, Long themeId) {
        if (!reservationRepository.existsBySlotAndStatusConfirmed(date, timeId, themeId)) {
            return CONFIRMED;
        }
        return Status.WAITING;
    }
}
