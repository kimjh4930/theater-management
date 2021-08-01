package com.theater.admin.movie.domain.movie;

import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class Movie {
    private Long id;
    private final MovieTitle title;
    private final Name director;
    private final LocalDate openingDate;
    private final Actors actors;
    private final Grade grade;
    private final RunningTime runningTime;

    private Movie(Builder builder){
        this.id = builder.id;
        this.title = builder.title;
        this.director = builder.director;
        this.openingDate = builder.openingDate;
        this.actors = builder.actors;
        this.grade = builder.grade;
        this.runningTime = builder.runningTime;
    }

    public static class Builder {
        private Long id;
        private final MovieTitle title;
        private final Name director;
        private final LocalDate openingDate;
        private final Actors actors;
        private final Grade grade;
        private final RunningTime runningTime;

        public Builder(String title,
                       String director,
                       LocalDate openingDate,
                       List<String> actors,
                       String grade,
                       Integer runningTime) {
            this.title = new MovieTitle(title);
            this.director = new Name(director);
            this.openingDate = openingDate;
            this.actors = new Actors(actors);
            this.grade = Grade.get(grade);
            this.runningTime = new RunningTime(runningTime);
        }

        public Builder id (Long id){
            this.id = id;
            return this;
        }

        public Movie build() {
            return new Movie(this);
        }
    }
}
