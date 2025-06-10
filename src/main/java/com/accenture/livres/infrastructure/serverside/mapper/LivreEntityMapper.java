package com.accenture.livres.infrastructure.serverside.mapper;


import com.accenture.livres.application.domain.Livre;
import com.accenture.livres.infrastructure.serverside.entity.LivreEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LivreEntityMapper {


    @Mapping(target = "createdDate", source = "dateCreation")
    LivreEntity livreToEntity(Livre livre);

    @Mapping(target = "dateCreation", source = "createdDate")
    Livre entityToLivre(LivreEntity livreEntity);
}
