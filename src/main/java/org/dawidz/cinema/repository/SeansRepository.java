package org.dawidz.cinema.repository;

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
    Page<SeansDto> findSeansesByRoomId(Long roomId, Pageable pageable);
}
