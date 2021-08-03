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
        MovieEntity entity = new MovieEntity(movie);
        entityManager.persist(entity);
        return entity.getId();
    }

    @Override
    public Long update(Movie updatedMovie) {
        MovieEntity oldMovie = Optional.ofNullable(entityManager.find(MovieEntity.class, updatedMovie.getId()))
                .orElseThrow(() -> new NullPointerException("수정할 대상이 존재하지 않습니다."));

        oldMovie.updateMovie(updatedMovie);

        return oldMovie.getId();
    }

    @Override
    public List<Movie> findAll() {
        return entityManager.createQuery("select m from MovieEntity m", MovieEntity.class)
                .getResultList()
                .stream()
                .map(entity -> toMovie(entity))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return Optional.ofNullable(entityManager.find(MovieEntity.class, id))
                .map(this::toMovie);
    }

    @Override
    public boolean isNotExist (Long id){
        return entityManager.find(MovieEntity.class, id) == null;
    }

    private Movie toMovie (MovieEntity entity){
        return new Movie.Builder(
                    entity.getTitle(),
                    entity.getDirector(),
                    entity.getOpeningDate(),
                    entity.getActors(),
                    entity.getGrade(),
                    entity.getRunningTime()
                )
                .id(entity.getId())
                .version(entity.getVersion())
                .build();
    }
}
