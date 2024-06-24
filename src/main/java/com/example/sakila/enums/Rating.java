package com.example.sakila.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum Rating {
    G("G"),
    PG("PG"),
    PG_13("PG-13"),
    R("R"),
    NC_17("NC-17");


    private final String rating;

    private Rating(String rating) {
        this.rating = rating;
    }

}



