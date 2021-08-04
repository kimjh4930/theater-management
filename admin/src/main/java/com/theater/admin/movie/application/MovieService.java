package com.theater.admin.movie.application;

import com.theater.admin.movie.adapter.presentation.web.NewMovie;
import com.theater.admin.movie.adapter.presentation.web.StoredMovie;
import com.theater.admin.movie.adapter.presentation.web.UpdatedMovie;
import com.theater.admin.movie.domain.movie.Movie;
import com.theater.admin.movie.domain.movie.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Transactional
    public Long create(final NewMovie newMovie){
        Movie movie = new Movie.Builder(
                newMovie.getTitle(),
                newMovie.getDirector(),
                newMovie.getOpeningDate(),
                newMovie.getActors(),
                newMovie.getGrade(),
                newMovie.getRunningTime()
        ).build();

        return movieRepository.save(movie);
    }

    @Transactional
    public Long update (final UpdatedMovie updatedMovie){
        if(movieRepository.isNotExist(updatedMovie.getId())) {
            throw new NullPointerException("수정할 대상이 존재하지 않습니다.");
        }

        Movie updated = new Movie.Builder(
                updatedMovie.getTitle(),
                updatedMovie.getDirector(),
                updatedMovie.getOpeningDate(),
                updatedMovie.getActors(),
                updatedMovie.getGrade(),
                updatedMovie.getRunningTime())
            .id(updatedMovie.getId())
        .build();

        return movieRepository.update(updated);
    }

    @Transactional(readOnly = true)
    public List<StoredMovie> findAll (){
        return movieRepository.findAll()
                .stream()
                .map(StoredMovie::new)
                .collect(Collectors.toUnmodifiableList());
    }

    @Transactional(readOnly = true)
    public StoredMovie findOne (Long id){
        return movieRepository.findById(id)
                .map(StoredMovie::new)
                .orElseThrow(() -> new NullPointerException("찾는 영화가 없습니다."));
    }

    @Transactional(readOnly = true)
    public StoredMovie findByTitle (String title){
        return movieRepository.findByTitle(title)
                .map(StoredMovie::new)
                .orElseThrow(() -> new NullPointerException("찾는 영화가 없습니다."));
    }
}
