package br.edu.iftm.rastreamento.advice;

import br.edu.iftm.rastreamento.service.exceptions.NaoAcheiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NaoAcheiException.class)
    public ResponseEntity<String> handlePacoteNotFoundException(NaoAcheiException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("{\"error\": \"" + ex.getMessage() + "\"}");
    }
}
