package com.accenture.livres.application.service;

import com.accenture.livres.application.domain.Livre;
import com.accenture.livres.application.exception.LivreException;
import com.accenture.livres.application.exception.LivreIntrouvableException;
import com.accenture.livres.application.port.input.LivreUseCase;
import com.accenture.livres.application.port.output.LivrePort;

import java.time.LocalDate;
import java.util.List;

public class LivreService implements LivreUseCase {

    private final LivrePort livrePort;

    public LivreService(LivrePort livrePort) {
        this.livrePort = livrePort;
    }


    @Override
    public List<Livre> livres() {
        return livrePort.findAll();
    }

    @Override
    public Livre trouver(int id){
        return livrePort
                .findById(id)
                .orElseThrow(() -> new LivreIntrouvableException("Livre introuvable avec l'id " + id));
    }

    @Override
    public Livre ajouter(Livre livre){
        verifierLivre(livre);
        return livrePort.ajouter(livre);
    }

    @Override
    public Livre modifier(int id, Livre livre){
        Livre livreEnBase = trouver(id);
        modifier(livre, livreEnBase);

        return livrePort.modifier(livreEnBase);
    }

    @Override
    public void supprimer(int id){
        boolean trouve = livrePort.existe(id);
        if (!trouve) {
            throw new LivreIntrouvableException("Livre introuvable avec l'id " + id);
        }
        livrePort.supprimer(id);
    }


    private static void modifier(Livre livre, Livre livreEnBase) {
        if (livre.getTitre() != null) {
            livreEnBase.setTitre(livre.getTitre());
        }
        if (livre.getAuteur() != null) {
            livreEnBase.setAuteur(livre.getAuteur());
        }
        if (livre.getIsbn() != null) {
            livreEnBase.setIsbn(livre.getIsbn());
        }
        if (livre.getDateParution() != null) {
            livreEnBase.setDateParution(livre.getDateParution());
        }
    }

    private void verifierLivre(Livre livre) {
        if (livre == null) {
            throw new LivreException("Le livre est null");
        }
        if (livre.getTitre() == null || livre.getTitre().isEmpty()) {
            throw new LivreException("Le titre du livre est null ou vide");
        }
        if (livre.getAuteur() == null || livre.getAuteur().isEmpty()) {
            throw new LivreException("L'auteur du livre est null ou vide");
        }
        if (livre.getIsbn() == null || livre.getIsbn().isEmpty()) {
            throw new LivreException("L'ISBN du livre est null ou vide");
        }
        if (livre.getDateParution() == null) {
            throw new LivreException("La date de parution du livre est null");
        }
        if (livre.getDateParution().isAfter(LocalDate.now())) {
            throw new LivreException("La date de parution du livre est dans le futur");
        }
    }
}
