package roomescape.reservation.repository.dto;

import roomescape.reservation.domain.Reservation;
import roomescape.reservation.domain.ReservationSlot;
import roomescape.reservation.domain.Status;
import roomescape.reservationtime.domain.ReservationTime;
import roomescape.theme.domain.Theme;

import java.time.LocalDate;

public record ReservationWaitingDto(
        Long id,
        String guestName,
        ReservationSlot slot,
        Status status,
        long waitNumber
) {
    public static ReservationWaitingDto from(Reservation reservation, long waitNumber) {
        return new ReservationWaitingDto(
                reservation.getId(),
                reservation.getGuestName(),
                reservation.getReservationSlot(),
                reservation.getStatus(),
                reservation.getStatus() == roomescape.reservation.domain.Status.WAITING ? waitNumber : 0
        );
    }

    public LocalDate date() {
        return slot.getDate();
    }

    public ReservationTime time() {
        return slot.getTime();
    }

    public Theme theme() {
        return slot.getTheme();
    }
}
