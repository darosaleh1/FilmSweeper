package com.example.sakila.output;

import com.example.sakila.entities.Actor;
import com.example.sakila.entities.Film;
import lombok.Getter;

import java.util.List;
import java.util.Set;

@Getter
public class ActorDetailsOutput {
    private Short id;
    private String firstName;
    private String lastName;
    private List<FilmReferenceOutput> starredFilms;
    private String fullName;

    public ActorDetailsOutput(Actor actor) {
        this.id = actor.getId();
        this.firstName = actor.getFirstName();
        this.lastName = actor.getLastName();
        this.fullName = actor.getFullName();
        this.starredFilms = actor.getStarredFilms()
                .stream()
                .map(FilmReferenceOutput::new)
                .toList();
    }
}
