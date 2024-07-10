package com.example.sakila.output;

import com.example.sakila.entities.Film;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilmTitleDTO {

    private String title;

    public FilmTitleDTO(Film film){
        this.title = film.getTitle();
    }
}

