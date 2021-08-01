package com.theater.admin.movie.domain.movie;

import java.util.List;

public interface MovieRepository {
    Long save(MovieKt movie);
    List<MovieKt> findAll();
    MovieKt findById(Long id);
}
