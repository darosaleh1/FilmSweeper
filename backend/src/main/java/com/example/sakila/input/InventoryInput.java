package com.example.sakila.input;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class InventoryInput {

    @NotNull(groups = {ValidationGroup.Create.class})
    private Short filmId;

    @NotNull(groups = {ValidationGroup.Create.class})
    private Short storeId;

    @NotNull(groups = {ValidationGroup.Create.class})
    private Timestamp lastUpdate;
}
