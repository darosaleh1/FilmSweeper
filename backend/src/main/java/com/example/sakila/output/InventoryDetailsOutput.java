package com.example.sakila.output;


import com.example.sakila.entities.Inventory;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class InventoryDetailsOutput {
    private Short id;
    private FilmTitleDTO film;
    private Short storeId;
    private Timestamp lastUpdate;

    public InventoryDetailsOutput(Inventory inventory) {
        this.id = inventory.getId();
        this.film = new FilmTitleDTO(inventory.getFilm());
        this.storeId = inventory.getStoreId();
        this.lastUpdate = inventory.getLastUpdate();
    }
}
