package com.example.sakila.services;

import com.example.sakila.entities.Film;
import com.example.sakila.entities.Inventory;
import com.example.sakila.input.InventoryInput;
import com.example.sakila.output.FilmInventoryCountDTO;
import com.example.sakila.output.InventoryDetailsOutput;
import com.example.sakila.repositories.FilmRepository;
import com.example.sakila.repositories.InventoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private FilmRepository filmRepository;

    public InventoryDetailsOutput createInventoryEntry(InventoryInput data) {
        final var inventoryEntry = new Inventory();

        Film film = filmRepository.findById(data.getFilmId())
                .orElseThrow(() -> new EntityNotFoundException("Film not found with id:" + data.getFilmId()));
        inventoryEntry.setFilm(film);
        inventoryEntry.setStoreId(data.getStoreId());
        inventoryEntry.setLastUpdate(data.getLastUpdate());

        Inventory created = inventoryRepository.save(inventoryEntry);
        return new InventoryDetailsOutput(created);
    }

    public Page<InventoryDetailsOutput> findAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Inventory> inventoryPage = inventoryRepository.findAll(pageable);
        return inventoryPage.map(InventoryDetailsOutput::new);
    }

    public InventoryDetailsOutput findById(Short id) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Inventory item not found with id:" + id));
        return new InventoryDetailsOutput(inventory);
    }

//    public Page<InventoryDetailsOutput> findByStoreId(Short storeId, int pageNo, int pageSize) {
//        Pageable pageable = PageRequest.of(pageNo, pageSize);
//        Page<Inventory> inventoryPage = inventoryRepository.findByStoreId(storeId, pageable);
//        return inventoryPage.map(InventoryDetailsOutput::new);
//    }

    public List<FilmInventoryCountDTO> findFilmInventoryCountByStoreId(Short storeId) {
        return inventoryRepository.findFilmInventoryCountByStoreId(storeId);
    }

    public List<FilmInventoryCountDTO> findFilmInventoryCountByFilmTitle(String title) {
        return inventoryRepository.findFilmInventoryCountByFilmTitle(title);
    }


    public InventoryDetailsOutput updateInventoryEntry(Short id, InventoryInput data) {
        return inventoryRepository.findById(id)
                .map(inventoryEntry -> {

                    if (data.getFilmId() != null){
                        Film film = filmRepository.findById(data.getFilmId())
                                .orElseThrow(() -> new EntityNotFoundException("Film not found with id:" + data.getFilmId()));
                        inventoryEntry.setFilm(film);
                    }



                    if (data.getStoreId() != null) inventoryEntry.setStoreId(data.getStoreId());
                    if (data.getLastUpdate() != null) inventoryEntry.setLastUpdate(data.getLastUpdate());
                    Inventory updated = inventoryRepository.save(inventoryEntry);
                    return new InventoryDetailsOutput(updated);
                })
                .orElseThrow(() ->
                        new EntityNotFoundException("Inventory item not found with id:" + id));
    }

    public void deleteInventoryEntry(Short id){
        inventoryRepository.deleteById(id);
    }

}
