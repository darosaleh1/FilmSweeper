package com.example.sakila.enums;

import java.util.HashMap;
import java.util.Map;

public enum Rating {
    G("G"),
    PG("PG"),
    PG_13("PG-13"),
    R("R"),
    NC_17("NC-17");

    public final String label;

    private Rating(String label) {
        this.label = label;
    }

    private static final Map<String, Rating> BY_LABEL = new HashMap<>();



//    public static Rating valueOfRating(String label) {
//        for (Rating r: values()) {
//            if (r.label.equals(label)) {
//                return r;
//            }
//        }
//        return null;
//    }




}