package com.theater.admin.movie.adapter.presentation.web;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@RequiredArgsConstructor
public class UpdatedMovie {
    @NotNull
    private final Long id;

    @NotBlank
    private final String title;

    @NotBlank
    private final String director;

    @NotNull
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private final LocalDate openingDate;

    private final List<String> actors;

    @NotBlank
    private final String grade;

    @Min(0)
    private final int runningTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdatedMovie that = (UpdatedMovie) o;
        return runningTime == that.runningTime && id.equals(that.id) && title.equals(that.title) && director.equals(that.director) && openingDate.equals(that.openingDate) && actors.equals(that.actors) && grade.equals(that.grade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, director, openingDate, actors, grade, runningTime);
    }

    @Override
    public String toString() {
        return "UpdatedMovie{" +
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
