package com.theater.admin.movie.adapter.presentation.web;

import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class UpdatedMovie {
    private Long id;
    private String title;
    private String director;
    private LocalDate openingDate;
    private List<String> actors;
    private String grade;
    private int runningTime;
}
