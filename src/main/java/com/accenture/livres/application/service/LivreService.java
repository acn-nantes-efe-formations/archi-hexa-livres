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
    public List<Livre> consulterLivres() {
        return livrePort.recupererTousLesLivres();
    }

    @Override
    public Livre consulterLivre(int id){
        Livre livre = livrePort
                .recupererLivreParId(id)
                .orElseThrow(() -> new LivreIntrouvableException("Livre introuvable avec l'id " + id));
        if (livre.getDateCreation() == null) {
            throw new LivreException("La date de creation du livre est null");
        }

        return livre;
    }

    @Override
    public Livre ajouterLivre(Livre livre){
        verifierLivre(livre);
        return livrePort.enregistrerNouveauLivre(livre);
    }

    @Override
    public Livre modifierLivre(int id, Livre livre){
        Livre livreEnBase = consulterLivre(id);
        modifier(livre, livreEnBase);

        return livrePort.mettreAJourLivre(livreEnBase);
    }

    @Override
    public void supprimerLivre(int id){
        boolean trouve = livrePort.existeLivreParId(id);
        if (!trouve) {
            throw new LivreIntrouvableException("Livre introuvable avec l'id " + id);
        }
        livrePort.supprimerLivreParId(id);
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
