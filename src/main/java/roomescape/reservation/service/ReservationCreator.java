package roomescape.reservation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import roomescape.common.exception.DomainException;
import roomescape.reservation.domain.Reservation;
import roomescape.reservation.domain.ReservationSlot;
import roomescape.reservation.domain.Status;
import roomescape.reservation.repository.ReservationRepository;
import roomescape.reservation.repository.ReservationSlotRepository;
import roomescape.reservation.service.validator.ReservationValidator;
import roomescape.reservationtime.domain.ReservationTime;
import roomescape.theme.domain.Theme;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static roomescape.reservation.domain.Status.CONFIRMED;
import static roomescape.reservation.exception.ReservationErrorCode.RESERVATION_SLOT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ReservationCreator {
    private final ReservationRepository reservationRepository;
    private final ReservationSlotRepository reservationSlotRepository;
    private final ReservationValidator reservationValidator;
    private final Clock clock;

    @Transactional
    public Reservation createReservation(String guestName, LocalDate date, ReservationTime time, Theme theme) {
        ReservationSlot lockSlot = getAndLockReservationSlot(date, time, theme);
        Status status = determineState(date, time.getId(), theme.getId());
        Reservation reservation = Reservation.create(guestName, lockSlot, status, LocalDateTime.now(clock));
        reservationValidator.validateCreate(reservation);
        return reservationRepository.save(reservation);
    }

    private ReservationSlot getAndLockReservationSlot(LocalDate date, ReservationTime time, Theme theme) {
        ReservationSlot slot = ReservationSlot.create(date, time, theme);
        ReservationSlot savedSlot = reservationSlotRepository.upsert(slot);

        return reservationSlotRepository.findByIdWithLock(savedSlot.getId())
                .orElseThrow(() -> new DomainException(RESERVATION_SLOT_NOT_FOUND));
    }

    private Status determineState(LocalDate date, Long timeId, Long themeId) {
        if (!reservationRepository.existsBySlotAndStatusConfirmed(date, timeId, themeId)) {
            return CONFIRMED;
        }
        return Status.WAITING;
    }
}
