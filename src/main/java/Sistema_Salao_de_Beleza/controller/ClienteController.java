package Sistema_Salao_de_Beleza.controller;
import Sistema_Salao_de_Beleza.dto.ClienteDTO;
import Sistema_Salao_de_Beleza.entity.ClienteModel;
import Sistema_Salao_de_Beleza.service.ClienteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/adicionar")
    public ResponseEntity<ClienteDTO> adicionarClinete(@RequestBody ClienteDTO clienteDTO){
        ClienteDTO cliente = clienteService.adicionarCliente(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    @GetMapping()
    public ResponseEntity<List<ClienteDTO>> listarAll(){
        List<ClienteDTO> lista = clienteService.listarClientes();
        if (lista.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable Long id){
        try{
            ClienteDTO cliente = clienteService.listarPorId(id);
            return ResponseEntity.ok().body(cliente);
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO){
        try{
            ClienteDTO cliente = clienteService.alterarCliente(id, clienteDTO);
            return ResponseEntity.ok().body(cliente);
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarCliente (@PathVariable Long id){
        try{
            clienteService.deletarCliente(id);
            return ResponseEntity.ok().body("Cliente com ID " + id + " deletado com sucesso.");
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
