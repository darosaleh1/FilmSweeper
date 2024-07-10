package com.example.sakila.repositories;

import com.example.sakila.entities.Film;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Year;
import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Short> {

    List<Film> findAllByReleaseYear(Year year, Pageable pageable);
}
