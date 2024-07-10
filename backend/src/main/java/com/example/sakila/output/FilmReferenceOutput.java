package com.example.sakila.output;

import com.example.sakila.entities.Actor;
import com.example.sakila.entities.Film;
import com.example.sakila.entities.Language;
import lombok.Data;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.Year;
import java.util.List;
import java.util.Set;

@Data
public class FilmReferenceOutput {
    private Short id;
    private String title;
    private Year releaseYear;

    public FilmReferenceOutput(Film film) {
        this.id = film.getId();
        this.title = film.getTitle();
        this.releaseYear = film.getReleaseYear();
    }


}