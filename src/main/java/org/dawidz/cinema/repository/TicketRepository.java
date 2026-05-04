package org.dawidz.cinema.repository;

import org.dawidz.cinema.dto.SeansDto;
import org.dawidz.cinema.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    //6
    @Query("""
            select new org.dawidz.cinema.dto.SeansDto(
                        s.id,
                        s.movie.id,
                        s.movie.title,
                        s.room.id,
                        s.room.roomNumber,
                        s.dateTime
                        )
            from Ticket t
            join t.seans s
            where t.client.id = :clientId
            """)
    Page<SeansDto> findSeansesByClientId(Long clientId, Pageable pageable);

    //7
    @Query("""
            select new org.dawidz.cinema.dto.SeansDto(
                        s.id,
                        s.movie.id,
                        s.movie.title,
                        s.room.id,
                        s.room.roomNumber,
                        s.dateTime
                        )
            from Ticket t
            join t.seans s
            where t.client.login = :login
            """)
    Page<SeansDto> findSeansesByClientLogin(String login, Pageable pageable);

    //10
    @Query("""
            select count(t.id)
            from Ticket t
            where t.client.id = :clientId
            and t.dateBought between :from and :to
            and t.status = org.dawidz.cinema.model.enums.TicketStatus.CONFIRMED
            """)
    long countTicketsByClientIdBetweenDates(Long clientId, LocalDate from, LocalDate to);
}
