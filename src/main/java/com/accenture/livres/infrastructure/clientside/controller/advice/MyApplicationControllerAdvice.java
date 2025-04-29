package com.accenture.livres.infrastructure.clientside.controller.advice;

import com.accenture.livres.application.exception.LivreException;
import com.accenture.livres.application.exception.LivreIntrouvableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class MyApplicationControllerAdvice {

    @ExceptionHandler(LivreException.class)
    public ResponseEntity<String> handleLivreException(LivreException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(LivreIntrouvableException.class)
    public ResponseEntity<String> handleLivreIntrouvableException(LivreIntrouvableException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}

