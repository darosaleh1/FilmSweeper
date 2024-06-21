package com.example.sakila.input;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.sql.Timestamp;
import java.time.Year;

@Data
public class FilmInput {

    @NotNull(groups = {ValidationGroup.Create.class})
    @Size(min = 1, max = 45)
    private String title;

    @Size(min = 1, max = 1000)
    private String description;


    private Year releaseYear;

    @NotNull(groups = {ValidationGroup.Create.class})
    private Byte languageId;

    private Integer originalLanguageId;


    @NotNull(groups = {ValidationGroup.Create.class})
    private Integer rentalDurationInDays;

    @NotNull(groups = {ValidationGroup.Create.class})
    private double rentalRate;


    private Integer lengthInMinutes;

    @NotNull(groups = {ValidationGroup.Create.class})
    private double replacementCost;

    private String rating;

    private String specialFeatures;

    @NotNull(groups = {ValidationGroup.Create.class})
    private Timestamp lastUpdate;
}
