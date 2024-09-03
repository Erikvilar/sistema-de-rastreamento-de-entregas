package br.edu.iftm.rastreamento.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iftm.rastreamento.model.Endereco;
import br.edu.iftm.rastreamento.model.Pacote;
import br.edu.iftm.rastreamento.model.Rastreamento;
import br.edu.iftm.rastreamento.repository.EnderecoRepository;
import br.edu.iftm.rastreamento.repository.PacoteRepository;
import br.edu.iftm.rastreamento.repository.RastreamentoRepository;
import br.edu.iftm.rastreamento.service.exceptions.NaoAcheiException;

@Service
public class PacoteService {

    @Autowired
    private PacoteRepository pacoteRepository;

    @Autowired 
    private EnderecoRepository enderecoRepository;
    @Autowired
    private RastreamentoRepository rastreamentoRepository;

    public List<Pacote> getAllPacotes() {
        Iterable<Pacote> pacotesIterable = pacoteRepository.findAll();
        List<Pacote> pacotesList = new ArrayList<>();
        pacotesIterable.forEach(pacotesList::add);
        return pacotesList;
    }

    public Pacote getPacoteById(Long id) {
        return pacoteRepository.findById(id).orElseThrow(() -> new NaoAcheiException("Pacote com ID " + id + " não encontrado"));
    }

    public Pacote createPacote(Pacote pacote) {

    Endereco endereco = pacote.getEndereco();
    if (endereco != null) {
      
        if (endereco.getId() == null) {
            endereco = enderecoRepository.save(endereco); 
        } else {
    
            if (!enderecoRepository.existsById(endereco.getId())) {
                throw new NaoAcheiException("Endereço com ID " + endereco.getId() + " não encontrado.");
            }
        }
    }

    pacote.setEndereco(endereco); 
    return pacoteRepository.save(pacote);
}

    public Pacote updatePacote(Long id, Pacote pacoteDetails) {

        Pacote pacote = pacoteRepository.findById(id).orElseThrow(() -> new NaoAcheiException("Pacote com ID " + id + " não encontrado"));

        // Atualiza os detalhes do pacote
        pacote.setIdUnico(pacoteDetails.getIdUnico());
        pacote.setDestinatario(pacoteDetails.getDestinatario());
        pacote.setStatus(pacoteDetails.getStatus());

        // Salva o pacote atualizado
        return pacoteRepository.save(pacote);

    }

    public void deletePacote(Long id) {
        Pacote pacote = pacoteRepository.findById(id).get();
        pacoteRepository.delete(pacote);
    }
    public List<Pacote> getStatus(String status){
        return pacoteRepository.findByStatus(status);
    }
    public List<Pacote> getDestinatario(String destinatario){
        return pacoteRepository.findByDestinatario(destinatario);
    }
}
