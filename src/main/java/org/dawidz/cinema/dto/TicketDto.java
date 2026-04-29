package org.dawidz.cinema.dto;

import org.dawidz.cinema.model.enums.TicketStatus;
import org.dawidz.cinema.model.enums.TicketType;

import java.time.LocalDate;

public record TicketDto(
        Long id,
        Long clientId,
        String clientLogin,
        Long seansId,
        String movieName,
        Long seatId,
        int seatRow,
        int seatNumber,
        TicketStatus status,
        TicketType type,
        LocalDate dateBought
) {

}
