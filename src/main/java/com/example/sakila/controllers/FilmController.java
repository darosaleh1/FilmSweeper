package com.example.sakila.controllers;

import com.example.sakila.entities.Actor;
import com.example.sakila.entities.Film;
import com.example.sakila.entities.Language;
import com.example.sakila.input.ActorInput;
import com.example.sakila.input.FilmInput;
import com.example.sakila.input.ValidationGroup;
import com.example.sakila.output.ActorDetailsOutput;
import com.example.sakila.output.FilmDetailsOutput;
import com.example.sakila.respositories.FilmRepository;
import com.example.sakila.respositories.LanguageRepository;
import com.example.sakila.services.FilmService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmController {

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private FilmService filmService;

    @GetMapping
    public List<FilmDetailsOutput> getFilms() {
        return filmService.findAll();
    }

    @GetMapping("/{id}")
    public FilmDetailsOutput getFilmById(@PathVariable Short id){
        return filmService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FilmDetailsOutput createFilm(@Validated(ValidationGroup.Create.class) @RequestBody FilmInput data) {
        return filmService.createFilm(data);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FilmDetailsOutput updateFilm(@Validated(ValidationGroup.Update.class) @RequestBody FilmInput data, @PathVariable Short id) {
        return filmService.updateFilm(id,data);
    }



    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteFilm(@PathVariable Short id) {
        filmService.deleteFilm(id);
    }
}
