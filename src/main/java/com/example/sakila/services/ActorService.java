package com.example.sakila.services;

import com.example.sakila.entities.Actor;
import com.example.sakila.entities.Film;
import com.example.sakila.input.ActorInput;
import com.example.sakila.output.ActorDetailsOutput;
import com.example.sakila.respositories.ActorRepository;
import com.example.sakila.respositories.FilmRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActorService {

    @Autowired
    private ActorRepository actorRepository;

    public ActorDetailsOutput createActor(ActorInput data) {
        final var actor = new Actor();
        actor.setFirstName(data.getFirstName());
        actor.setLastName(data.getLastName());
        Actor created = actorRepository.save(actor);

        return new ActorDetailsOutput(created);
    }

    public List<Actor> findAll() {
        return actorRepository.findAll();
    }

    public ActorDetailsOutput findById(Short id) {
        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Actor not found with id:" + id));
        return new ActorDetailsOutput(actor);
    }

    public List<Actor> findByName(String name) {
        return actorRepository.findByFullNameContainingIgnoreCase(name);
    }

    public ActorDetailsOutput updateActor(Short id, ActorInput data) {
        return actorRepository.findById(id)
                .map(actor -> {
                    if (data.getFirstName() != null) actor.setFirstName(data.getFirstName());
                    if (data.getLastName() != null) actor.setLastName(data.getLastName());
                    Actor updated = actorRepository.save(actor);
                    return new ActorDetailsOutput(updated);
                })
                .orElseThrow(() ->
                            new EntityNotFoundException("Actor not found with id:" + id));
    }

    public void deleteActor(Short id){
        actorRepository.deleteById(id);
    }



}
