package com.theater.admin.movie.adapter.infrastructure.jpa;

import com.theater.admin.movie.domain.movie.Movie;
import com.theater.admin.movie.domain.movie.MovieRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class H2MovieDao implements MovieRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    public H2MovieDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

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
