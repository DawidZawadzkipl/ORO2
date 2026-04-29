package org.dawidz.cinema.mapper;

import org.dawidz.cinema.dto.SeansDto;
import org.dawidz.cinema.model.Seans;
import org.dawidz.cinema.model.Seat;
import org.springframework.stereotype.Component;

@Component
public class SeansMapper {

    public SeansDto toDto(Seans seans) {
        return new SeansDto(
                seans.getId(),
                seans.getMovie().getId(),
                seans.getMovie().getTitle(),
                seans.getRoom().getId(),
                seans.getRoom().getRoomNumber(),
                seans.getDateTime()
        );
    }
}
