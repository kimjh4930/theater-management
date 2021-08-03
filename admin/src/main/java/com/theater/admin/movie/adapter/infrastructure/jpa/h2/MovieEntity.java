package com.theater.admin.movie.adapter.infrastructure.jpa.h2;

import com.theater.admin.movie.domain.movie.Movie;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Table(name = "movie")
public class MovieEntity {
    @Id @GeneratedValue
    @Column(name = "movie_id")
    private Long id;

    @Version
    private Integer version;

    private String title;
    private String director;
    private LocalDate openingDate;

    @ElementCollection
    private List<String> actors = new ArrayList<>();
    private String grade;
    private int runningTime;

    protected MovieEntity (Movie movie) {
        this.title = movie.getTitle();
        this.director = movie.getDirector();
        this.openingDate = movie.getOpeningDate();
        this.actors = new ArrayList<>(movie.getActors());
        this.grade = movie.getGrade();
        this.runningTime = movie.getRunningTime();
    }

    protected MovieEntity() { }

    public void updateMovie (Movie updatedMovie){
        this.title = updatedMovie.getTitle();
        this.director = updatedMovie.getDirector();
        this.openingDate = updatedMovie.getOpeningDate();
        this.actors = updatedMovie.getActors();
        this.grade = updatedMovie.getGrade();
        this.runningTime = updatedMovie.getRunningTime();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieEntity that = (MovieEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "MovieEntity{" +
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
