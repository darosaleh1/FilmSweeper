package com.example.sakila.controllers;

import com.example.sakila.entities.Film;
import com.example.sakila.input.FilmInput;
import com.example.sakila.input.ValidationGroup;
import com.example.sakila.output.FilmDetailsOutput;
import com.example.sakila.repositories.FilmRepository;
import com.example.sakila.repositories.LanguageRepository;
import com.example.sakila.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
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
    public Page getFilms(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        return filmService.findAll(pageNo,pageSize);
    }

    @GetMapping("/{id}")
    public FilmDetailsOutput getFilmById(@PathVariable Short id){
        return filmService.findById(id);
    }

    @GetMapping("/year/{year}")
    public List<Film> getFilmsByReleaseYear(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @PathVariable int year) {
        Year releaseYear = Year.of(year);
        return filmService.findAllByReleaseYear(pageNo, pageSize, releaseYear);
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
