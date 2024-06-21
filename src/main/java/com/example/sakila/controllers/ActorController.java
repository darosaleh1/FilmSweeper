package com.example.sakila.controllers;

import com.example.sakila.entities.Actor;
import com.example.sakila.input.ActorInput;
import com.example.sakila.input.ValidationGroup;
import com.example.sakila.input.ValidationGroup.*;
import com.example.sakila.output.ActorDetailsOutput;
import com.example.sakila.respositories.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/actors")
public class ActorController {

    @Autowired
    private ActorRepository actorRepository;

    @GetMapping
    public List<ActorDetailsOutput> getActors(){
        return actorRepository.findAll()
                .stream()
                .map(ActorDetailsOutput::new)
                .toList();
    }

    @GetMapping("/{id}")
    public ActorDetailsOutput getActorById(@PathVariable Short id) {
        return actorRepository.findById(id)
                .map(ActorDetailsOutput::new)
                .orElseThrow(() ->
                    new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            String.format("No such actor with id %d.", id)
                    ));
            }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ActorDetailsOutput createActor(@Validated(Create.class) @RequestBody ActorInput data) {
        Actor actor = new Actor();
        actor.setFirstName(data.getFirstName());
        actor.setLastName(data.getLastName());

        Actor created = actorRepository.save(actor);

        return new ActorDetailsOutput(created);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ActorDetailsOutput updateActor(@Validated(Update.class) @RequestBody ActorInput data, @PathVariable Short id) {

        return actorRepository.findById(id)
                .map(actor -> {
                    actor.setFirstName(data.getFirstName());
                    actor.setLastName(data.getLastName());
                    Actor updated = actorRepository.save(actor);
                    return new ActorDetailsOutput(updated);
                })
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("No such actor with id %d.", id)
                        ));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteActor(@PathVariable Short id) {
        actorRepository.deleteById(id);
    }


}
