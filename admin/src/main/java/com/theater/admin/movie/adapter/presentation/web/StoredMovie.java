package com.theater.admin.movie.adapter.presentation.web;

import com.theater.admin.movie.domain.movie.Movie;

import java.time.LocalDate;
import java.util.List;

public class StoredMovie {
    private Long id;
    private String title;
    private String director;
    private LocalDate openingDate;
    private List<String> actors;
    private String grade;
    private int runningTime;

    public StoredMovie (Movie movie){
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.director = movie.getDirector();
        this.openingDate = movie.getOpeningDate();
        this.actors = movie.getActors();
        this.grade = movie.getGrade();
        this.runningTime = movie.getRunningTime();
    }
}
