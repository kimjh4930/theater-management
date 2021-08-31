package com.theater.admin.movie.adapter.presentation.web;

import com.theater.admin.movie.application.MovieService;
import com.theater.admin.movie.domain.movie.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/movies")
public class MovieRestController {
    private final MovieService movieService;
    private final MovieRequestValidator movieRequestValidator;
    private final MovieRepository movieRepository;

    @GetMapping
    public ResponseEntity<List<StoredMovie>> findAll (){
        List<StoredMovie> storedMovies = movieRepository.findAll().stream()
                .map(StoredMovie::new)
                .collect(Collectors.toUnmodifiableList());
        return new ResponseEntity<>(storedMovies, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoredMovie> findOne (@PathVariable("id") Long id){
        StoredMovie findMovie = movieRepository.findById(id)
                .map(StoredMovie::new)
                .orElseThrow(() -> new NullPointerException());
        return new ResponseEntity<>(findMovie, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity add (@Valid @RequestBody NewMovie newMovie, Errors errors) {
        if(errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(errors);
        }

        movieRequestValidator.validate(newMovie, errors);

        if(errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(errors);
        }

        Long id = movieService.create(newMovie);

        return new ResponseEntity(id, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity update (
            @PathVariable("id") Long id,
            @Valid @RequestBody UpdatedMovie updatedMovie,
            Errors errors
            ) {

        if(errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(errors);
        }

        movieRequestValidator.validate(updatedMovie, errors);

        if(errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(errors);
        }

        Long updatedId = movieService.update(updatedMovie);

        return new ResponseEntity(updatedId, HttpStatus.OK);
    }
}
