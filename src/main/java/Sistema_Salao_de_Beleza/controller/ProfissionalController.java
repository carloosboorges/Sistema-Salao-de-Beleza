package Sistema_Salao_de_Beleza.controller;
import Sistema_Salao_de_Beleza.dto.ProfissionalDTO;
import Sistema_Salao_de_Beleza.service.ProfissionalService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profissional")
public class ProfissionalController {

    private final ProfissionalService profissionalService;

    public ProfissionalController(ProfissionalService profissionalService) {
        this.profissionalService = profissionalService;
    }


    @PostMapping("/adicionar")
    public ResponseEntity<ProfissionalDTO> adicionarProfissional(@RequestBody ProfissionalDTO profissionalDTO){
        ProfissionalDTO profissional = profissionalService.adicionarProficional(profissionalDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(profissional);
    }

    @GetMapping()
    public ResponseEntity<List<ProfissionalDTO>> listarAll (){
        List<ProfissionalDTO> lista = profissionalService.listaDeProfissional();
        if(lista.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable Long id){
        try{
            ProfissionalDTO profissional = profissionalService.listarPorID(id);
            return ResponseEntity.ok().body(profissional);
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> alterarProfissional(@PathVariable Long id, @RequestBody ProfissionalDTO profissionalDTO){
        try{
            ProfissionalDTO profissional = profissionalService.alterarProfissional(id, profissionalDTO);
            return ResponseEntity.ok().body(profissional);
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarProfissional(@PathVariable Long id){
        try{
            profissionalService.deletarProfissiona(id);
            return ResponseEntity.ok().body("Profissional com ID " + id + " deleteado com sucesso.");
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
