package org.dawidz.cinema.dto;

import java.time.LocalDateTime;

public record SeansDto(
        Long id,
        String movieTitle,
        LocalDateTime dateTime,
        Long RoomId
){}