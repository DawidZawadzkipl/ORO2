package org.dawidz.cinema.mapper;

import org.dawidz.cinema.dto.RoomDto;
import org.dawidz.cinema.model.Room;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper {

    public RoomDto toDto(Room room) {
        return new RoomDto(room.getId(), room.getRoomNumber());
    }
}
