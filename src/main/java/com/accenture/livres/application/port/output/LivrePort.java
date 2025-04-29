package com.accenture.livres.application.port.output;

import com.accenture.livres.application.domain.Livre;

import java.util.List;
import java.util.Optional;

public interface LivrePort {
    List<Livre> findAll();

    Optional<Livre> findById(int id);

    Livre ajouter(Livre livre);

    Livre modifier(Livre livre);

    void supprimer(int id);

    boolean existe(int id);
}
