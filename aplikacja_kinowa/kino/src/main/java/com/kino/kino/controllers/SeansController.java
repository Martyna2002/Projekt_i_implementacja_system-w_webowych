package com.kino.kino.controllers;

import com.kino.kino.models.Seans;
import com.kino.kino.repositories.SeansRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seanse")
@CrossOrigin(origins = "http://localhost:4200") 
public class SeansController {

    private final SeansRepository seansRepo;

    public SeansController(SeansRepository seansRepo) {
        this.seansRepo = seansRepo;
    }

    @GetMapping
    public List<Seans> getSeanse() {
        return seansRepo.findAll();
    }
}

