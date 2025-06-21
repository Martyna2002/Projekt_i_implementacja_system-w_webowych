package com.kino.kino.controllers;

import com.kino.kino.models.Film;
import com.kino.kino.repositories.FilmRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/filmy")
public class FilmController {

    private final FilmRepository filmRepo;

    public FilmController(FilmRepository filmRepo) {
        this.filmRepo = filmRepo;
    }

    @GetMapping
    public List<Film> getFilmy() {
        return filmRepo.findAll();
    }
}
