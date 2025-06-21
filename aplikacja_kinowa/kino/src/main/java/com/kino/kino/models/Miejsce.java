package com.kino.kino.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Miejsce {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int numer; // np. 1, 2, 3 itd.

    @ManyToOne
    private Sala sala;
}
