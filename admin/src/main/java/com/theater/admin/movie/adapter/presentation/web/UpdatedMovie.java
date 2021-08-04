package com.theater.admin.movie.adapter.presentation.web;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Getter
@RequiredArgsConstructor
public class UpdatedMovie {
    private final Long id;
    private final String title;
    private final String director;
    private final LocalDate openingDate;
    private final List<String> actors;
    private final String grade;
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
