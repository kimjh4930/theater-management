package com.theater.admin.movie.domain.movie;

import java.util.List;
import java.util.Optional;

public interface MovieRepository {
    Movie save(Movie movie);
    List<Movie> findAll();
    Optional<Movie> findById(Long id);
}
