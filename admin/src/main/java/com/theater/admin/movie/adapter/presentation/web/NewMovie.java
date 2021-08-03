package com.theater.admin.movie.adapter.presentation.web;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class NewMovie {
    private String title;
    private String director;
    private LocalDate openingDate;
    private List<String> actors = new ArrayList<>();
    private String grade;
    private Integer runningTime;

    public NewMovie (String title,
                     String director,
                     LocalDate openingDate,
                     List<String> actors,
                     String grade,
                     Integer runningTime){
        this.title = title;
        this.director = director;
        this.openingDate = openingDate;
        this.actors = actors;
        this.grade = grade;
        this.runningTime = runningTime;
    }

//    public static class Builder {
//        private String name;
//        private String director;
//        private LocalDateTime openingDate;
//        private List<String> actors = new ArrayList<>();
//        private String nation;
//        private String grade;w
//        private int runningTime;
//
//        public Builder name(String name){
//            this.name = name;
//            return this;
//        }
//    }
}
