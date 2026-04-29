package org.dawidz.cinema.dto;

import java.time.LocalDateTime;

public record SeansDto(
        Long id,
        Long movieId,
        String movieTitle,
        Long RoomId,
        int roomNumber,
        LocalDateTime dateTime
){}