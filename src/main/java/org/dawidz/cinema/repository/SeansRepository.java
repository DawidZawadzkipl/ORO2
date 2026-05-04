package org.dawidz.cinema.repository;

import org.dawidz.cinema.dto.ClientDto;
import org.dawidz.cinema.dto.SeansDto;
import org.dawidz.cinema.model.Movie;
import org.dawidz.cinema.model.Seans;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface SeansRepository extends JpaRepository<Seans, Long> {
    //2 lista seansow granych w sali o danym id
    @Query("""
        select new org.dawidz.cinema.dto.SeansDto(
            s.id,
            m.id,
            m.title,
            r.id,
            r.roomNumber,
            s.dateTime
        )
        from Seans s
        join s.movie m
        join s.room r
        where r.id = :roomId
""")
    Page<SeansDto> findSeanssByRoomId(Long roomId, Pageable pageable);
    //3
    @Query("""
            select new org.dawidz.cinema.dto.SeansDto(
                        s.id,
                        m.id,
                        m.title,
                        r.id,
                        r.roomNumber,
                        s.dateTime
                        )
            from Seans s
            join s.movie m
            join s.room r
            where m.id = :movieId
            and s.cancelled = false
            and m.active = true
            and r.active = true
            """)
    Page<SeansDto> findSeansOfMovieById(Long movieId, Pageable pageable);
    //4
    @Query("""
            select new org.dawidz.cinema.dto.SeansDto(
                        s.id,
                        m.id,
                        m.title,
                        r.id,
                        r.roomNumber,
                        s.dateTime
                        )
                        from Seans s
                        join s.movie m
                        join s.room r
                        where m.title = :movieTitle
                        and s.cancelled = false
                        and m.active = true
                        and r.active = true
            """)
    Page<SeansDto> findSeansByMovieTitle(String movieTitle, Pageable pageable);
    //5
    @Query("""
            select new org.dawidz.cinema.dto.ClientDto(
                        c.id,
                        c.name,
                        c.login
                        )
            from Seans s
            join s.tickets t
            join t.client c
            where s.id = :seansId
            and t.status = org.dawidz.cinema.model.enums.TicketStatus.CONFIRMED
            """)
    Page<ClientDto> findClientsBySeansId(Long seansId, Pageable pageable);

    //8
    @Query("""
        select count(t)
        from Seans s
        join s.tickets t
        where s.id = :seansId
        and t.status <> org.dawidz.cinema.model.enums.TicketStatus.EXPIRED
        """)
    long totalOccupiedSeatsBySeansId(Long seansId);

    //9
    @Query("""
            select count(distinct s.room.id)
            from Seans s
            where s.movie.id = :movieId
            """)
    long countRoomsByMovieId(Long movieId);
}
