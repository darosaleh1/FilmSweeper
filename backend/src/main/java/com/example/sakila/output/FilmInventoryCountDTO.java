package com.example.sakila.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class FilmInventoryCountDTO {
    private Short storeId;
    private String title;
    private Long count;

    public FilmInventoryCountDTO(Short storeId, String title, Long count) {
        this.storeId = storeId;
        this.title = title;
        this.count = count;
    }
}