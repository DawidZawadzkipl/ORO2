package org.dawidz.cinema.mapper;

import org.dawidz.cinema.dto.MovieDto;
import org.dawidz.cinema.model.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {
    public MovieDto toDto(Movie movie) {
        return new MovieDto(movie.getId(), movie.getTitle());
    }
}
