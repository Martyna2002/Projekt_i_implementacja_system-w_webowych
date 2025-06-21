package com.kino.kino.controllers;

import com.kino.kino.models.Bilet;
import com.kino.kino.models.Seans;
import com.kino.kino.repositories.RezerwacjaRepository;
import com.kino.kino.repositories.SeansRepository;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rezerwacje")
@CrossOrigin(origins = "http://localhost:4200")
public class BiletController {

    private final RezerwacjaRepository repo;
    private final SeansRepository seansRepo;

    public BiletController(RezerwacjaRepository repo, SeansRepository seansRepo) {
        this.repo = repo;
        this.seansRepo = seansRepo;
    }

    @PostMapping
    public ResponseEntity<String> zarezerwuj(@RequestBody RezerwacjaRequest request) {
        Optional<Seans> seansOpt = seansRepo.findById(request.getSeansId());
        if (seansOpt.isEmpty()) return ResponseEntity.badRequest().build();

        // Sprawdź, które miejsca są już zajęte
        List<String> zajete = repo.findBySeans_Id(request.getSeansId()).stream()
                .flatMap(r -> Arrays.stream(r.getMiejsca().split(",")))
                .collect(Collectors.toList());

        // Jeśli jakiekolwiek wybrane miejsce już jest zajęte – błąd
        for (String m : request.getMiejsca()) {
            if (zajete.contains(m)) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Miejsce " + m + " jest już zajęte.");
            }
        }

        // Jeśli wszystkie wolne – zapisujemy
        String kod = UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        Bilet bilet = new Bilet();
        bilet.setKod(kod);
        bilet.setSeans(seansOpt.get());
        bilet.setMiejsca(String.join(",", request.getMiejsca()));
        bilet.setDataRezerwacji(LocalDateTime.now());
        bilet.setStatus("ważny");

        repo.save(bilet);
        return ResponseEntity.ok(kod);
    }

    @GetMapping("/{seansId}")
    public List<String> zajeteMiejsca(@PathVariable Long seansId) {
        return repo.findBySeans_Id(seansId).stream()
            .flatMap(r -> Arrays.stream(r.getMiejsca().split(",")))
            .collect(Collectors.toList());
    }
}

@Data
class RezerwacjaRequest {
    private Long seansId;
    private List<String> miejsca;
}
