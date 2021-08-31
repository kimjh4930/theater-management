package com.theater.admin.movie.domain.movie;

import java.util.List;
import java.util.Optional;

public interface MovieRepository {
    Long save(Movie movie);
    Long update(Movie updatedMovie);
    List<Movie> findAll();
    Optional<Movie> findById(Long id);
    Optional<Movie> findByTitle(String title);
    boolean isNotExist (Long id);
}
