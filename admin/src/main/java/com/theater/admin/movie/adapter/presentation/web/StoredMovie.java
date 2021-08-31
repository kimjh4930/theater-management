package com.theater.admin.movie.adapter.presentation.web;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.theater.admin.movie.domain.movie.Movie;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class StoredMovie {
    private Long id;
    private Integer version;
    private String title;
    private String director;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate openingDate;
    private List<String> actors;
    private String grade;
    private int runningTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public StoredMovie (Movie movie){
        this.id = movie.getId();
        this.version = movie.getVersion();
        this.title = movie.getTitle();
        this.director = movie.getDirector();
        this.openingDate = movie.getOpeningDate();
        this.actors = movie.getActors();
        this.grade = movie.getGrade();
        this.runningTime = movie.getRunningTime();
        this.createdAt = movie.getCreatedAt();
        this.updatedAt = movie.getUpdatedAt();
    }

    @Override
    public String toString() {
        return "StoredMovie{" +
                "id=" + id +
                ", version=" + version +
                ", title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", openingDate=" + openingDate +
                ", actors=" + actors +
                ", grade='" + grade + '\'' +
                ", runningTime=" + runningTime +
                '}';
    }
}
