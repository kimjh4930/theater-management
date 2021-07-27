package com.theater.admin.movie.adapter.presentation.web;

import com.theater.admin.movie.application.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController("/movie")
@RequiredArgsConstructor
public class MovieRestController {
    private final MovieService movieService;

    @PostMapping
    public ResponseEntity<StoredMovie> add (@RequestBody NewMovie newMovie) throws URISyntaxException {
        URI redirectUri = new URI("/movie");
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(redirectUri);
        return new ResponseEntity<>(headers, HttpStatus.PERMANENT_REDIRECT);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<StoredMovie> findOne (@RequestAttribute("id") Long id){
        return null;
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<StoredMovie>> findAll (){
        return null;
    }

}
