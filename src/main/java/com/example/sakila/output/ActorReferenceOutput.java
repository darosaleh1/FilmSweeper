package com.example.sakila.output;

import com.example.sakila.entities.Actor;
import com.example.sakila.entities.Film;
import lombok.Getter;

import java.util.List;

@Getter
public class ActorReferenceOutput {
    private Short id;
    private String fullName;

    public ActorReferenceOutput(Actor actor) {
        this.id = actor.getId();
        this.fullName = actor.getFullName();
    }
}
