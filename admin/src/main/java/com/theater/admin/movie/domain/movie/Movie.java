package com.theater.admin.movie.domain.movie;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "movie")
@EntityListeners(AuditingEntityListener.class)
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long id;

    @Version
    private Integer version;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "title", column = @Column(name = "title"))
    })
    private MovieTitle title;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "director"))
    })
    private Name director;

    private LocalDate openingDate;

    @Embedded
    private Actors actors = new Actors();

    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Embedded
    private RunningTime runningTime;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    protected Movie () {}

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void update (Movie updatedData){
        this.title = new MovieTitle(updatedData.getTitle());
        this.director = new Name(updatedData.getDirector());
        this.openingDate = updatedData.getOpeningDate();
        this.actors = new Actors(updatedData.getActors());
        this.grade = Grade.get(updatedData.getGrade());
        this.runningTime = new RunningTime(updatedData.getRunningTime());
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id.equals(movie.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
