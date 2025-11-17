package Sistema_Salao_de_Beleza.controller;
import Sistema_Salao_de_Beleza.dto.AgendamentoRequestDTO;
import Sistema_Salao_de_Beleza.dto.AgendamentoResponseDTO;
import Sistema_Salao_de_Beleza.service.AgendamentoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }


    @PostMapping("/adicionar")
    public ResponseEntity<AgendamentoResponseDTO> adicionarAgendamento(@RequestBody AgendamentoRequestDTO agendamentoDTO){
        AgendamentoResponseDTO novoAgendamento = agendamentoService.fazerAgendamento(agendamentoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAgendamento);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<AgendamentoResponseDTO>> listarAll(){
        List<AgendamentoResponseDTO> lista = agendamentoService.listarAgendamentos();
        if (lista.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        try{
            AgendamentoResponseDTO agendamentoEncontrado = agendamentoService.listarPorId(id);
            return ResponseEntity.ok(agendamentoEncontrado);
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarAgendamento(@PathVariable Long id, @RequestBody AgendamentoRequestDTO agendamentoDTO){
        try{
            AgendamentoResponseDTO agendamento = agendamentoService.alterarAgendamento(id, agendamentoDTO);
            return ResponseEntity.ok(agendamento);
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarAgendamento(Long id){
        try{
            agendamentoService.deletarAgendamento(id);
            return ResponseEntity.ok().body("Agendamento com ID" + id + " deletado com sucesso.");
        }catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
