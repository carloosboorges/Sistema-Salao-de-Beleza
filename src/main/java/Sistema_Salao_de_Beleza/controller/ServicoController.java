package Sistema_Salao_de_Beleza.controller;

import Sistema_Salao_de_Beleza.dto.ServicoDTO;
import Sistema_Salao_de_Beleza.service.ServicoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
@RequestMapping("/servico")
public class ServicoController {

    private final ServicoService servicoService;
    private final RestClient.Builder builder;


    public ServicoController(ServicoService servicoService, RestClient.Builder builder) {
        this.servicoService = servicoService;
        this.builder = builder;
    }

    @PostMapping("/adicionar")
    public ResponseEntity<ServicoDTO> adicionarServico(@RequestBody ServicoDTO servicoDTO) {
        ServicoDTO servico = servicoService.adicionarServico(servicoDTO);
        return ResponseEntity.ok().body(servico);
    }

    @GetMapping()
    public ResponseEntity<List<ServicoDTO>> listarAll() {
        List<ServicoDTO> lista = servicoService.listaDeServico();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable Long id) {
        try {
            ServicoDTO servico = servicoService.listarPorId(id);
            return ResponseEntity.ok().body(servico);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> altualizarServico(@PathVariable Long id, @RequestBody ServicoDTO servicoDTO) {
        try {
            ServicoDTO servicoAtualizado = servicoService.atualizarServico(id, servicoDTO);
            return ResponseEntity.ok().body(servicoAtualizado);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarServico(@PathVariable Long id) {
        try {
            servicoService.deletarServico(id);
            return ResponseEntity.ok().body("Servico com ID " + id + " deletado com sucesso.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}