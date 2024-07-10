package com.example.sakila.entities;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Table(name = "inventory")
@Entity
@Getter
@Setter
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Short id;


    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    @Column(name = "store_id")
    private Short storeId;

    @Column(name= "last_update")
    private Timestamp lastUpdate;



}
