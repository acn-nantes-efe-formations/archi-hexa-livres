package com.accenture.livres.application.port.input;

import com.accenture.livres.application.domain.Livre;

import java.util.List;

public interface LivreUseCase {
    List<Livre> consulterLivres();

    Livre consulterLivre(int id);

    Livre ajouterLivre(Livre livre);

    Livre modifierLivre(int id, Livre livre);

    void supprimerLivre(int id);
}
