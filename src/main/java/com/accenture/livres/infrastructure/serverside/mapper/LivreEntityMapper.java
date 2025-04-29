package com.accenture.livres.infrastructure.serverside.mapper;


import com.accenture.livres.application.domain.Livre;
import com.accenture.livres.infrastructure.serverside.entity.LivreEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LivreEntityMapper {

    LivreEntity livreToEntity(Livre livre);
    Livre entityToLivre(LivreEntity livreEntity);
}
