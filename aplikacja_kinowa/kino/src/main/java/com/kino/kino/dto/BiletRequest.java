package com.kino.kino.dto;

import lombok.Data;

import java.util.List;

@Data
public class BiletRequest {
    private Long seansId;
    private List<String> miejsca;
}
