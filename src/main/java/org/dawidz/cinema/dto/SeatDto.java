package org.dawidz.cinema.dto;

public record SeatDto(
        Long id,
        int row,
        int seatNumber,
        Long roomId
) {
}
