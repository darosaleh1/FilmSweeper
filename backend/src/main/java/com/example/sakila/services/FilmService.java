package com.example.sakila.services;


import com.example.sakila.entities.Film;
import com.example.sakila.entities.Language;
import com.example.sakila.input.FilmInput;
import com.example.sakila.output.FilmDetailsOutput;
import com.example.sakila.repositories.FilmRepository;
import com.example.sakila.repositories.LanguageRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;

@Service
public class FilmService {

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private LanguageRepository languageRepository;

    public FilmDetailsOutput createFilm(FilmInput data) {
        final var film = new Film();
        film.setTitle(data.getTitle());
        film.setDescription(data.getDescription());
        film.setReleaseYear(data.getReleaseYear());

        Language language = languageRepository.findById(data.getLanguageId())
                .orElseThrow(() -> new EntityNotFoundException("Language not found with id:" + data.getLanguageId()));
        film.setLanguage(language);
        film.setOriginalLanguageId(data.getOriginalLanguageId());
        film.setRentalDurationInDays(data.getRentalDurationInDays());
        film.setRentalRate(data.getRentalRate());
        film.setLengthInMinutes(data.getLengthInMinutes());
        film.setReplacementCost(data.getReplacementCost());
        film.setRating(data.getRating());
        film.setLastUpdate(data.getLastUpdate());

        Film created = filmRepository.save(film);

        return new FilmDetailsOutput(created);
    }

    public Page<FilmDetailsOutput> findAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Film> filmPage = filmRepository.findAll(pageable);
        return filmPage.map(FilmDetailsOutput::new);
    }

    public List<Film> findAllByReleaseYear(int pageNo, int pageSize, Year year) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        List<Film> filmPage = filmRepository.findAllByReleaseYear(year,pageable);
        return filmPage;
    }

    public FilmDetailsOutput findById(Short id) {
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Film not found with id:" + id));
        return new FilmDetailsOutput(film);
    }

    public FilmDetailsOutput updateFilm(Short id, FilmInput data) {
        return filmRepository.findById(id)
                .map(film -> {
                    film.setTitle(data.getTitle());
                    film.setDescription(data.getDescription());
                    film.setReleaseYear(data.getReleaseYear());

                    Language language = languageRepository.findById(data.getLanguageId())
                            .orElseThrow(() ->
                                    new EntityNotFoundException("Language not found with id:" +data.getLanguageId()));
                    film.setLanguage((language));
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
                        new EntityNotFoundException("No film with id:" + id));
    }

    public void deleteFilm(Short id){
        filmRepository.deleteById(id);
    }
}
