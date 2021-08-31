package com.theater.admin.movie.adapter.presentation.web;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Builder
public class NewMovie {

    @NotBlank
    private String title;

    @NotBlank
    private String director;

    @NotNull
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate openingDate;

    private List<String> actors = new ArrayList<>();

    @NotBlank
    private String grade;

    @Min(0)
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
}
