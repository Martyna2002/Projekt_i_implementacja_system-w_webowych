package com.kino.kino.repositories;

import java.util.List;

import com.kino.kino.models.Miejsce;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MiejsceRepository extends JpaRepository<Miejsce, Long> {
    List<Miejsce> findBySalaId(Long salaId);
}
