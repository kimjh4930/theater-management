package com.theater.admin.movie.adapter.infrastructure.memory;

import com.theater.admin.movie.domain.movie.Movie;
import com.theater.admin.movie.domain.movie.MovieRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryMovieDao implements MovieRepository {
    private final Map<Long, Movie> entities = new HashMap<>();

    @Override
    public Movie save(Movie movie) {
        return null;
    }

    @Override
    public List<Movie> findAll() {
        return null;
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return Optional.empty();
    }
}
