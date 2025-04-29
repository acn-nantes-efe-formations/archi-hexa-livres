package com.accenture.livres.infrastructure.clientside.dto;

import java.time.LocalDate;

public record LivreRequestDto(
        String titre,
        String auteur,
        String isbn,
        LocalDate dateParution
)
{}
