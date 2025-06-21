package com.kino.kino.repositories;

import com.kino.kino.models.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Long> {}