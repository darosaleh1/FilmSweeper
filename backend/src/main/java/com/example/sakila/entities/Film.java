package com.example.sakila.entities;

import com.example.sakila.converters.RatingConverter;
import com.example.sakila.converters.SpecialFeaturesConverter;
import com.example.sakila.enums.Rating;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
    @JoinColumn(name= "language_id")
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
    @Convert(converter = RatingConverter.class)
    private Rating rating;

    @Column(name= "special_features")
    @Convert(converter = SpecialFeaturesConverter.class)
    private Set<String> specialFeatures = new HashSet<>();

    @Column(name= "last_update")
    private Timestamp lastUpdate;



    @ManyToMany
    @JoinTable(
            name = "film_actor",
            joinColumns = @JoinColumn(name="film_id"),
            inverseJoinColumns = @JoinColumn(name="actor_id")
    )
    private List<Actor> cast = new ArrayList<>();


}
