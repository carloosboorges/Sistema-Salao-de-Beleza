package Sistema_Salao_de_Beleza.service;
import Sistema_Salao_de_Beleza.dto.ProfissionalDTO;
import Sistema_Salao_de_Beleza.entity.ProfissionalModel;
import Sistema_Salao_de_Beleza.mapper.ProfissionalMapper;
import Sistema_Salao_de_Beleza.repository.ProfissionalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProfissionalService {


    public ProfissionalService(ProfissionalMapper profissionalMapper, ProfissionalRepository profissionalRepository) {
        this.profissionalMapper = profissionalMapper;
        this.profissionalRepository = profissionalRepository;
    }

    private final ProfissionalMapper profissionalMapper;
    private final ProfissionalRepository profissionalRepository;


    public ProfissionalDTO adicionarProficionarl(ProfissionalDTO profissionalDTO){
        ProfissionalModel novoProfissional = profissionalMapper.toModel(profissionalDTO);
        return profissionalMapper.toDto(profissionalRepository.save(novoProfissional));
    }

    public List<ProfissionalDTO> listaDeProfissional(){
        List<ProfissionalModel> lista = profissionalRepository.findAll();
        return lista.stream()
                .map(profissionalMapper::toDto)
                .toList();
    }

    public ProfissionalDTO listarPorID(Long id){
        return profissionalRepository.findById(id)
                .map(profissionalMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Profissional com ID " + id + " não encontrado."));
    }

    public ProfissionalDTO alterarProfissional(Long id, ProfissionalDTO profissionalDTO){
        ProfissionalModel profissionalEncontrado = profissionalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Profissional com ID " + id + " não encontrado."));

        ProfissionalModel profissionalAtualizado = profissionalMapper.toModel(profissionalDTO);
        profissionalAtualizado.setId(id);
        return profissionalMapper.toDto(profissionalRepository.save(profissionalAtualizado));
    }

    public void deletarProfissiona(Long id){
        if (!profissionalRepository.existsById(id)){
            throw new EntityNotFoundException("Profissional com ID " + id + " não encontrado.");
        }
        profissionalRepository.deleteById(id);
    }
}
