package br.edu.iftm.rastreamento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iftm.rastreamento.model.Pacote;
import br.edu.iftm.rastreamento.service.PacoteService;
import br.edu.iftm.rastreamento.service.exceptions.NaoAcheiException;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/pacotes")

public class PacoteController {

	@Autowired
	private PacoteService pacoteService;

	@GetMapping
	public List<Pacote> getAllPacotes() {
		return pacoteService.getAllPacotes();
	}

	@PostMapping

	public Pacote createPacote(@RequestBody Pacote pacote) {
		return pacoteService.createPacote(pacote);
	}

	@GetMapping("/{id}")
	    @Operation(summary = "Obter pacote por ID", description = "Retorna os detalhes de um pacote específico.")
	public Pacote getPacoteById(@PathVariable Long id) throws NaoAcheiException {
		return pacoteService.getPacoteById(id);
	}

	@GetMapping("status/{status}")
	@Operation(summary = "Obter pacote por Status", description = "Retorna os detalhes de pacotes específicos usando o status.")

	public List<Pacote>getPacoteByStatus(@PathVariable String status) throws NaoAcheiException {
		return pacoteService.getStatus(status);
	}
	@GetMapping("destinatario/{destinatario}")
	@Operation(summary = "Obter pacote por Destinatario", description = "Retorna os detalhes de  pacotes específicos usando destinatario.")

	public List<Pacote>getPacoteByDestinatario(@PathVariable String destinatario) throws NaoAcheiException {
		return pacoteService.getDestinatario(destinatario);
	}


	@PutMapping("/{id}")
	public Pacote updatePacote(@PathVariable Long id, @RequestBody Pacote pacoteDetails) throws NaoAcheiException{
		return pacoteService.updatePacote(id, pacoteDetails);
	}

	@DeleteMapping("/{id}")
	public void deletePacote(@PathVariable Long id) {
		pacoteService.deletePacote(id);
	}
}