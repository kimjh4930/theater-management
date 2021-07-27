package com.theater.admin.movie.adapter.presentation.web;

import com.theater.admin.movie.domain.movie.Grade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StoredMovie {
    private Long id;
    private String name;
    private String director;
    private Date openingDate;
    private List<String> actors = new ArrayList<>();
    private String nation;
    private Grade grade;
    private int runningTime;
}
