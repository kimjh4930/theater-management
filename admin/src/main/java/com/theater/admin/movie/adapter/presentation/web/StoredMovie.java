package com.theater.admin.movie.adapter.presentation.web;

import com.theater.admin.movie.domain.movie.Movie;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
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

    @Override
    public String toString() {
        return "StoredMovie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", openingDate=" + openingDate +
                ", actors=" + actors +
                ", grade='" + grade + '\'' +
                ", runningTime=" + runningTime +
                '}';
    }
}
