package org.dawidz.cinema.repository;

import org.dawidz.cinema.dto.MovieDto;
import org.dawidz.cinema.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    //1.lista filmow granych w sali o danym id
    @Query("""
           select distinct new org.dawidz.cinema.dto.MovieDto(
                      m.id,
                      m.title
           )
          from Seans s 
          join s.movie m
          where s.room.id = :roomId
          and m.active = true
          and s.cancelled = false
           """)
    Page<MovieDto> findMoviesByRoomId(Long roomId, Pageable pageable);
}
