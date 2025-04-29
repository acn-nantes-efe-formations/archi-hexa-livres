package com.accenture.livres.infrastructure.serverside.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;


@Entity
@Table(name = "livre")
public class LivreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titre;
    private String auteur;
    private String isbn;
    private LocalDate dateParution;

    public LivreEntity() {
    }

    public LivreEntity(String titre, String auteur, String isbn, LocalDate dateParution) {
        this.titre = titre;
        this.auteur = auteur;
        this.isbn = isbn;
        this.dateParution = dateParution;
    }

    public LivreEntity(int id, String titre, String auteur, String isbn, LocalDate dateParution) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.isbn = isbn;
        this.dateParution = dateParution;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getDateParution() {
        return dateParution;
    }

    public void setDateParution(LocalDate dateParution) {
        this.dateParution = dateParution;
    }
}
