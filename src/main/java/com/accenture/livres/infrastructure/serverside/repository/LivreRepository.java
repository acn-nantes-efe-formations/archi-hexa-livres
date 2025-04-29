package com.accenture.livres.infrastructure.serverside.repository;

import com.accenture.livres.infrastructure.serverside.entity.LivreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivreRepository extends JpaRepository<LivreEntity, Integer> {
}
