package com.theater.admin.movie.domain.movie;

import java.time.LocalDate;
import java.util.List;

public class Movie {
    private Long id;
    private Integer version;
    private final MovieTitle title;
    private final Name director;
    private final LocalDate openingDate;
    private final Actors actors;
    private final Grade grade;
    private final RunningTime runningTime;

    private Movie(Builder builder){
        this.id = builder.id;
        this.version = builder.version;
        this.title = builder.title;
        this.director = builder.director;
        this.openingDate = builder.openingDate;
        this.actors = builder.actors;
        this.grade = builder.grade;
        this.runningTime = builder.runningTime;
    }

    public Long getId (){
        return this.id;
    }

    public Integer getVersion () {
        return this.version;
    }

    public String getTitle (){
        return title.valueOf();
    }

    public String getDirector (){
        return director.getName();
    }

    public LocalDate getOpeningDate (){
        return openingDate;
    }

    public List<String> getActors (){
        return this.actors.getActors();
    }

    public String getGrade (){
        return this.grade.getGrade();
    }

    public Integer getRunningTime (){
        return this.runningTime.valueOf();
    }

    public static class Builder {
        private Long id;
        private Integer version;
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

        public Builder version (Integer version){
            this.version = version;
            return this;
        }

        public Movie build() {
            return new Movie(this);
        }
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", version=" + version +
                ", title=" + title +
                ", director=" + director +
                ", openingDate=" + openingDate +
                ", actors=" + actors +
                ", grade=" + grade +
                ", runningTime=" + runningTime +
                '}';
    }
}
