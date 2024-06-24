package com.example.sakila.converters;

import jakarta.persistence.AttributeConverter;

import javax.print.attribute.Attribute;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SpecialFeaturesConverter implements AttributeConverter<Set<String>, String> {

    @Override
    public String convertToDatabaseColumn(Set<String> specialFeatures) {
        if (specialFeatures == null || specialFeatures.isEmpty()) {
            return null;
        }
        return String.join(",", specialFeatures);
    }

    @Override
    public Set<String> convertToEntityAttribute(String data) {
        if (data == null || data.isEmpty()) {
            return new HashSet<>();
        }
        return new HashSet<>(Arrays.asList(data.split(",")));
    }
}
