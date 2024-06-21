package com.example.sakila.output;

import com.example.sakila.entities.Film;
import com.example.sakila.entities.Language;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.Year;

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
    private String rating;
    private String specialFeatures;
    private Timestamp lastUpdate;

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
    }
}
