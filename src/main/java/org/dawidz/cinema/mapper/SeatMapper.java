package org.dawidz.cinema.mapper;

import org.dawidz.cinema.dto.SeatDto;
import org.dawidz.cinema.model.Seat;
import org.springframework.stereotype.Component;

@Component
public class SeatMapper {
    public SeatDto toDto(Seat seat) {
        return new SeatDto(seat.getId(), seat.getRow(), seat.getRow(),seat.getRoom().getId());
    }
}
