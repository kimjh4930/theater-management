package com.theater.admin.movie.domain.movie;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Embeddable
public class Actors {

    @ElementCollection
    @CollectionTable(name = "movie_entity_actors", joinColumns = @JoinColumn(name = "movie_entity_movie_id"))
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "actors"))
    })
    private List<Name> actors = new ArrayList<>();

    public Actors () {}

    public Actors(List<String> actors) {
        this.actors = actors
                .stream()
                .map(Name::new)
                .collect(Collectors.toList());
    }

    public List<String> getActors (){
        return actors.stream()
                .map(Name::getName)
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actors actors1 = (Actors) o;
        return Objects.equals(actors, actors1.actors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actors);
    }

    @Override
    public String toString() {
        return "Actors{" +
                "actors=" + actors +
                '}';
    }
}
