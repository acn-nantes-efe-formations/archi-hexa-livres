package com.accenture.livres.application.port.input;

import com.accenture.livres.application.domain.Livre;

import java.util.List;

public interface LivreUseCase {
    List<Livre> livres();

    Livre trouver(int id);

    Livre ajouter(Livre livre);

    Livre modifier(int id, Livre livre);

    void supprimer(int id);
}
