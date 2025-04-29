package com.accenture.livres.infrastructure.clientside.mapper;


import com.accenture.livres.application.domain.Livre;
import com.accenture.livres.infrastructure.clientside.dto.LivreRequestDto;
import com.accenture.livres.infrastructure.clientside.dto.LivreResponseDto;
import com.accenture.livres.infrastructure.serverside.entity.LivreEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LivreDtoMapper {

    LivreResponseDto livreToDtoResponse(Livre livre);
    Livre requestDtoToLivre(LivreRequestDto livreRequestDto);
}
