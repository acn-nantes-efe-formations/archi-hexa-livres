package com.accenture.livres.application.port.output;

import com.accenture.livres.application.domain.Livre;

import java.util.List;
import java.util.Optional;

public interface LivrePort {
    List<Livre> recupererTousLesLivres();

    Optional<Livre> recupererLivreParId(int id);

    Livre enregistrerNouveauLivre(Livre livre);

    Livre mettreAJourLivre(Livre livre);

    void supprimerLivreParId(int id);

    boolean existeLivreParId(int id);
}
