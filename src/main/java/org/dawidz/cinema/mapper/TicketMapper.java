package org.dawidz.cinema.mapper;

import org.dawidz.cinema.dto.TicketDto;
import org.dawidz.cinema.model.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {

    public TicketDto toDto(Ticket ticket){
        return new TicketDto(
                ticket.getId(),
                ticket.getClient().getId(),
                ticket.getClient().getLogin(),
                ticket.getSeans().getId(),
                ticket.getSeans().getMovie().getTitle(),
                ticket.getSeat().getId(),
                ticket.getSeat().getRow(),
                ticket.getSeat().getSeatNumber(),
                ticket.getStatus(),
                ticket.getType(),
                ticket.getDateBought()
        );
    }
}
