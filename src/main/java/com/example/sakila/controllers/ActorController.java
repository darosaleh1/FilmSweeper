package com.example.sakila.controllers;

import com.example.sakila.entities.Actor;
import com.example.sakila.input.ActorInput;
import com.example.sakila.input.ValidationGroup;
import com.example.sakila.input.ValidationGroup.*;
import com.example.sakila.output.ActorDetailsOutput;
import com.example.sakila.respositories.ActorRepository;
import com.example.sakila.services.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/actors")
public class ActorController {

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private ActorService actorService;

    @GetMapping
    public List<ActorDetailsOutput> getActors(@RequestParam(required = false) Optional<String> name){

        return name.map(value -> actorService.findByName(value))
                .orElseGet(() -> actorService.findAll())
                .stream()
                .map(ActorDetailsOutput::new)
                .toList();
    }

    @GetMapping("/{id}")
    public ActorDetailsOutput getActorById(@PathVariable Short id) {
        return actorService.findById(id);
            }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ActorDetailsOutput createActor(@Validated(Create.class) @RequestBody ActorInput data) {
        return actorService.createActor(data);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ActorDetailsOutput updateActor(@Validated(Update.class) @RequestBody ActorInput data, @PathVariable Short id) {
        return actorService.updateActor(id,data);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteActor(@PathVariable Short id) {
        actorService.deleteActor(id);
    }


}
