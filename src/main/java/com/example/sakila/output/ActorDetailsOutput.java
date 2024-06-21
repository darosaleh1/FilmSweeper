package com.example.sakila.output;

import com.example.sakila.entities.Actor;
import lombok.Getter;

@Getter
public class ActorDetailsOutput {
    private Short id;
    private String firstName;
    private String lastName;

    public ActorDetailsOutput(Actor actor) {
        this.id = actor.getId();
        this.firstName = actor.getFirstName();
        this.lastName = actor.getLastName();
    }
}
