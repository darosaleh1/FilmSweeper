package com.example.sakila.repositories;

import com.example.sakila.entities.Inventory;
import com.example.sakila.output.FilmInventoryCountDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Short> {

    @Query("SELECT new com.example.sakila.output.FilmInventoryCountDTO(i.storeId, f.title, COUNT(i.id)) " +
            "FROM Inventory i " +
            "JOIN i.film f " +
            "WHERE i.storeId = :storeId " +
            "GROUP BY i.storeId, f.title")
    List<FilmInventoryCountDTO> findFilmInventoryCountByStoreId(@Param("storeId") Short storeId);

    @Query("SELECT new com.example.sakila.output.FilmInventoryCountDTO(i.storeId, f.title, COUNT(i.id)) " +
            "FROM Inventory i " +
            "JOIN i.film f " +
            "WHERE f.title = :title " +
            "GROUP BY i.storeId, f.title")
    List<FilmInventoryCountDTO> findFilmInventoryCountByFilmTitle(@Param("title") String title);
}
