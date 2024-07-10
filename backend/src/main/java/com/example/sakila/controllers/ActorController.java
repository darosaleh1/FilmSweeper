package com.example.sakila.controllers;

import com.example.sakila.input.ActorInput;
import com.example.sakila.input.ValidationGroup.*;
import com.example.sakila.output.ActorDetailsOutput;
import com.example.sakila.repositories.ActorRepository;
import com.example.sakila.services.ActorService;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/actors")
public class ActorController {

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private ActorService actorService;

    @GetMapping
    public Page getActors(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {

        return actorService.findAll(pageNo, pageSize);

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
