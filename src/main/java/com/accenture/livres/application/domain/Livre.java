package com.accenture.livres.application.domain;

import java.time.LocalDate;

public class Livre {

    private int id;
    private String titre;
    private String auteur;
    private String isbn;
    private LocalDate dateParution;

    public Livre() {
    }

    public Livre(String titre, String auteur, String isbn, LocalDate dateParution) {
        this.titre = titre;
        this.auteur = auteur;
        this.isbn = isbn;
        this.dateParution = dateParution;
    }

    public Livre(int id, String titre, String auteur, String isbn, LocalDate dateParution) {
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
