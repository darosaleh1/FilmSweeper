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

    @GetMapping
    public List<FilmDetailsOutput> getFilms() {
        return filmRepository.findAll()
                .stream()
                .map(FilmDetailsOutput::new)
                .toList();
    }

    @GetMapping("/{id}")
    public FilmDetailsOutput getFilmById(@PathVariable Short id){
        return filmRepository.findById(id)
                .map(FilmDetailsOutput::new)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("No such film with id %d.", id)
                        ));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FilmDetailsOutput createFilm(@Validated(ValidationGroup.Create.class) @RequestBody FilmInput data) {
        Film film = new Film();
        film.setTitle(data.getTitle());
        film.setDescription(data.getDescription());
        film.setReleaseYear(data.getReleaseYear());

        Language language = languageRepository.findById(data.getLanguageId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
                film.setLanguage(language);

        film.setOriginalLanguageId(data.getOriginalLanguageId());
        film.setRentalDurationInDays(data.getRentalDurationInDays());
        film.setRentalRate(data.getRentalRate());
        film.setLengthInMinutes(data.getLengthInMinutes());
        film.setReplacementCost(data.getReplacementCost());
        film.setRating(data.getRating());
        film.setSpecialFeatures(data.getSpecialFeatures());
        film.setLastUpdate(data.getLastUpdate());

        Film created = filmRepository.save(film);

        return new FilmDetailsOutput(created);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FilmDetailsOutput updateFilm(@Validated(ValidationGroup.Update.class) @RequestBody FilmInput data, @PathVariable Short id) {

        return filmRepository.findById(id)
                .map(film -> {
                    film.setTitle(data.getTitle());
                    film.setDescription(data.getDescription());
                    film.setReleaseYear(data.getReleaseYear());
//                    film.setLanguageId(data.getLanguageId());
                    film.setOriginalLanguageId(data.getOriginalLanguageId());
                    film.setRentalDurationInDays(data.getRentalDurationInDays());
                    film.setRentalRate(data.getRentalRate());
                    film.setLengthInMinutes(data.getLengthInMinutes());
                    film.setReplacementCost(data.getReplacementCost());
                    film.setRating(data.getRating());
                    film.setSpecialFeatures(data.getSpecialFeatures());
                    film.setLastUpdate(data.getLastUpdate());
                    Film updated = filmRepository.save(film);
                    return new FilmDetailsOutput(updated);
                })
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("No such film with id %d.", id)
                        ));
    }



    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteFilm(@PathVariable Short id) {
        filmRepository.deleteById(id);
    }


}
