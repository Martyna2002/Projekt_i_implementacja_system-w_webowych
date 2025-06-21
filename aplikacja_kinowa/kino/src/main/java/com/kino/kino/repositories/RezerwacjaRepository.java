package com.kino.kino.repositories;

import com.kino.kino.models.Bilet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RezerwacjaRepository extends JpaRepository<Bilet, String> {
    List<Bilet> findBySeans_Id(Long seansId);
}
