package com.theater.admin.movie.adapter.presentation.web;

import com.theater.admin.movie.application.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController("/movie")
public class MovieRestController {
    private final MovieService movieService;

    @Autowired
    public MovieRestController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<StoredMovie> findOne (@PathVariable("id") Long id){
        return ResponseEntity.ok(movieService.findOne(id));
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<StoredMovie>> findAll (){
        return ResponseEntity.ok(movieService.findAll());
    }

    @PostMapping
    public ResponseEntity add (@RequestBody NewMovie newMovie) throws URISyntaxException {
        Long id = movieService.create(newMovie);

        return ResponseEntity
                .status(HttpStatus.PERMANENT_REDIRECT)
                .location(new URI("/" + id))
                .build();
    }
}
