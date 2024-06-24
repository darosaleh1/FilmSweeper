package com.example.sakila.output;

import com.example.sakila.entities.Actor;
import com.example.sakila.entities.Film;
import com.example.sakila.entities.Language;
import com.example.sakila.enums.Rating;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.Year;
import java.util.List;
import java.util.Set;

@Getter
public class FilmDetailsOutput {
    private Short id;
    private String title;
    private String description;
    private Year releaseYear;
    private Language language;
    private Integer originalLanguageId;
    private Integer rentalDurationInDays;
    private double rentalRate;
    private Integer lengthInMinutes;
    private double replacementCost;
    private Rating rating;
    private Set<String> specialFeatures;
    private Timestamp lastUpdate;
    private List<ActorReferenceOutput> cast;

    public FilmDetailsOutput(Film film) {
        this.id = film.getId();
        this.title = film.getTitle();
        this.description = film.getDescription();
        this.releaseYear = film.getReleaseYear();
        this.language = film.getLanguage();
        this.originalLanguageId = film.getOriginalLanguageId();
        this.rentalDurationInDays = film.getRentalDurationInDays();
        this.rentalRate = film.getRentalRate();
        this.lengthInMinutes = film.getLengthInMinutes();
        this.replacementCost = film.getReplacementCost();
        this.rating = film.getRating();
        this.specialFeatures = film.getSpecialFeatures();
        this.lastUpdate = film.getLastUpdate();
        this.cast = film.getCast()
                .stream()
                .map(ActorReferenceOutput::new)
                .toList();
    }
}
