package com.theater.admin.movie.adapter.infrastructure.jpa.h2;

import com.theater.admin.movie.domain.movie.Movie;
import com.theater.admin.movie.domain.movie.MovieRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class H2MovieRepository implements MovieRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public H2MovieRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Long save(Movie movie) {
        entityManager.persist(movie);
        entityManager.flush();

        return movie.getId();
    }

    @Override
    public Long update(Movie updatedMovie) {
        Movie oldMovie = Optional.ofNullable(entityManager.find(Movie.class, updatedMovie.getId()))
                .orElseThrow(() -> new NullPointerException("수정할 대상이 존재하지 않습니다."));

        oldMovie.update(updatedMovie);

        entityManager.flush();

        return oldMovie.getId();
    }

    @Override
    public List<Movie> findAll() {
        return entityManager.createQuery("select m from Movie m", Movie.class)
                .getResultList();
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Movie.class, id));
    }

    @Override
    public Optional<Movie> findByTitle(String title) {
        Movie findMovie = entityManager.createQuery(
                "select m from Movie m where m.title.title =: title", Movie.class)
                .setParameter("title", title)
                .getSingleResult();

        return Optional.ofNullable(findMovie);
    }

    @Override
    public boolean isNotExist (Long id){
        return entityManager.find(MovieEntity.class, id) == null;
    }
}
