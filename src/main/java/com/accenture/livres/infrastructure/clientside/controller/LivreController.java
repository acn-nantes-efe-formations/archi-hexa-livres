package com.accenture.livres.application.port.input;

import com.accenture.livres.application.domain.Livre;
import com.accenture.livres.infrastructure.clientside.dto.LivreRequestDto;
import com.accenture.livres.infrastructure.clientside.dto.LivreResponseDto;
import com.accenture.livres.infrastructure.clientside.mapper.LivreDtoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livres")
public class LivreController {

    private final LivreUseCase livreUseCase;
    private final LivreDtoMapper livreDtoMapper;

    public LivreController(LivreUseCase livreUseCase, LivreDtoMapper livreDtoMapper) {
        this.livreUseCase = livreUseCase;
        this.livreDtoMapper = livreDtoMapper;
    }

    @GetMapping
    public ResponseEntity<List<LivreResponseDto>> getAll() {
        List<LivreResponseDto> liste = livreUseCase.consulterLivres()
                .stream()
                .map(livreDtoMapper::livreToDtoResponse).toList();
        return ResponseEntity.ok(liste);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivreResponseDto> getById(@PathVariable int id) {
        Livre livre = livreUseCase.consulterLivre(id);
        return ResponseEntity.ok(livreDtoMapper.livreToDtoResponse(livre));
    }

    @PostMapping
    public ResponseEntity<LivreResponseDto> create(@RequestBody LivreRequestDto livreRequestDto) {
        Livre livre = livreUseCase.ajouterLivre(livreDtoMapper.requestDtoToLivre(livreRequestDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(livreDtoMapper.livreToDtoResponse(livre));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<LivreResponseDto> update(@PathVariable int id, @RequestBody LivreRequestDto livreRequestDto) {
        Livre livre = livreDtoMapper.requestDtoToLivre(livreRequestDto);
        Livre livreModifie = livreUseCase.modifierLivre(id, livre);
        return ResponseEntity.ok(livreDtoMapper.livreToDtoResponse(livreModifie));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        livreUseCase.supprimerLivre(id);
        return ResponseEntity.noContent().build();
    }
}
