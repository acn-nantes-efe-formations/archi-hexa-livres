package com.accenture.livres.infrastructure.clientside.dto;

import java.time.LocalDate;

public record LivreResponseDto(
        int id,
        String titre,
        String auteur,
        String isbn,
        LocalDate dateParution
)
{}
