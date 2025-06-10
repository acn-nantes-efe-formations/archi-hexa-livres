package com.accenture.livres.application.domain;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
public class Livre {

    private int id;
    private String titre;
    private String auteur;
    private String isbn;
    private LocalDate dateParution;
    private LocalDateTime dateCreation;


}
