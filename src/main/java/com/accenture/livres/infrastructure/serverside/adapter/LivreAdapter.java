package com.accenture.livres.infrastructure.serverside.adapter;

import com.accenture.livres.application.domain.Livre;
import com.accenture.livres.application.port.output.LivrePort;
import com.accenture.livres.infrastructure.serverside.entity.LivreEntity;
import com.accenture.livres.infrastructure.serverside.mapper.LivreEntityMapper;
import com.accenture.livres.infrastructure.serverside.repository.LivreRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LivreAdapter implements LivrePort {

    private final LivreRepository livreRepository;
    private final LivreEntityMapper livreEntityMapper;

    public LivreAdapter(LivreRepository livreRepository, LivreEntityMapper livreEntityMapper) {
        this.livreRepository = livreRepository;
        this.livreEntityMapper = livreEntityMapper;
    }

    @Override
    public List<Livre> findAll() {
        return livreRepository.findAll()
                .stream()
                .map(livreEntityMapper::entityToLivre)
                .toList();
    }

    @Override
    public Optional<Livre> findById(int id) {
        return livreRepository
                .findById(id)
                .map(livreEntityMapper::entityToLivre);
    }

    @Override
    public Livre ajouter(Livre livre) {
        LivreEntity livreEntity = livreEntityMapper.livreToEntity(livre);
        LivreEntity savedLivreEntity = livreRepository.save(livreEntity);
        return livreEntityMapper.entityToLivre(savedLivreEntity);
    }

    @Override
    public Livre modifier(Livre livre) {
        LivreEntity livreEntity = livreEntityMapper.livreToEntity(livre);
        LivreEntity savedLivreEntity = livreRepository.save(livreEntity);
        return livreEntityMapper.entityToLivre(savedLivreEntity);
    }

    @Override
    public void supprimer(int id) {
        livreRepository.deleteById(id);
    }

    @Override
    public boolean existe(int id) {
        return livreRepository.existsById(id);
    }
}
