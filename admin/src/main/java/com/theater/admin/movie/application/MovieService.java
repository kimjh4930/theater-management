package com.theater.admin.movie.application;

import com.theater.admin.movie.domain.movie.Movie;
import com.theater.admin.movie.domain.movie.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie create(final Movie movie){
        return movieRepository.save(movie);
    }

    public List<Movie> list (){
        return movieRepository.findAll();
    }

    public Movie findOne (Long id){
        return movieRepository.findById(id).orElseThrow(() -> new NullPointerException());
    }
}
