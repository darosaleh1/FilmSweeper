package com.example.sakila.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Year;


@Entity
@Table(name = "film")
@Getter
@Setter
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    @Setter(AccessLevel.NONE)
    private Short id;

    @Column(name= "title")

    private String title;

    @Column(name= "description")

    private String description;

    @Column(name= "release_year")
    private Year releaseYear;

    @ManyToOne
    @Column(name= "language_id")
    private Language language;

    @Column(name= "original_language_id")
    private Integer originalLanguageId;

    @Column(name= "rental_duration")
    private Integer rentalDurationInDays;

    @Column(name= "rental_rate")
    private double rentalRate;

    @Column(name= "length")
    private Integer lengthInMinutes;

    @Column(name= "replacement_cost")
    private double replacementCost;

    @Column(name= "rating")
    private String rating;

    @Column(name= "special_features")
    private String specialFeatures;

    @Column(name= "last_update")
    private Timestamp LastUpdate;


}
