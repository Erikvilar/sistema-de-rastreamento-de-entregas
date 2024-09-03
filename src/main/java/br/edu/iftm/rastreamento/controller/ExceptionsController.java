package br.edu.iftm.rastreamento.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.edu.iftm.rastreamento.service.exceptions.NaoAcheiException;
import io.swagger.v3.oas.annotations.Operation;

@ControllerAdvice
public class ExceptionsController {
	@Operation(summary = "Handler para capturar excpetions", description = "Caso não haja o ID nas entidades ele retorna uma messagem de erro.")

	@ExceptionHandler(NaoAcheiException.class)
	public ResponseEntity<?> naoAchei(NaoAcheiException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}
}
