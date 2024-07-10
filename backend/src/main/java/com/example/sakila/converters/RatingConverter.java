package com.example.sakila.converters;

import com.example.sakila.enums.Rating;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class RatingConverter implements AttributeConverter<Rating, String> {

    @Override
    public String convertToDatabaseColumn(Rating rating) {
        if (rating == null) {
            return null;
        }
        return rating.getRating();
    }

    @Override
    public Rating convertToEntityAttribute(String rating) {
        if (rating == null) {
            return null;
        }
        return Stream.of(Rating.values())
                .filter(r -> r.getRating().equals(rating))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
