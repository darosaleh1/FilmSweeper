package com.example.sakila.controllers;

import com.example.sakila.input.ActorInput;
import com.example.sakila.input.InventoryInput;
import com.example.sakila.input.ValidationGroup;
import com.example.sakila.output.ActorDetailsOutput;
import com.example.sakila.output.FilmInventoryCountDTO;
import com.example.sakila.output.InventoryDetailsOutput;
import com.example.sakila.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    public Page getInventory(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {

        return inventoryService.findAll(pageNo, pageSize);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryDetailsOutput createInventoryEntry(@Validated(ValidationGroup.Create.class) @RequestBody InventoryInput data) {
        return inventoryService.createInventoryEntry(data);
    }

    @GetMapping("/{id}")
    public InventoryDetailsOutput getInventoryItemById(@PathVariable Short id) {
        return inventoryService.findById(id);
    }

    @GetMapping("/store/{storeId}/films")
    public List<FilmInventoryCountDTO> getFilmInventoryCountByStoreId(@PathVariable Short storeId) {
        return inventoryService.findFilmInventoryCountByStoreId(storeId);
    }

    @GetMapping("/store/{title}")
    public List<FilmInventoryCountDTO> searchByFilmTitle(@RequestParam String title) {
        return inventoryService.findFilmInventoryCountByFilmTitle(title);
    }



    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InventoryDetailsOutput updateInventoryEntry(@Validated(ValidationGroup.Update.class) @RequestBody InventoryInput data, @PathVariable Short id) {
        return inventoryService.updateInventoryEntry(id,data);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteInventoryEntry(@PathVariable Short id) {
        inventoryService.deleteInventoryEntry(id);
    }



}
