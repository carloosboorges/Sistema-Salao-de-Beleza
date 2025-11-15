package Sistema_Salao_de_Beleza.service;

import Sistema_Salao_de_Beleza.dto.AgendamentoDTO;
import Sistema_Salao_de_Beleza.entity.AgendamentoModel;
import Sistema_Salao_de_Beleza.mapper.AgendamentoMapper;
import Sistema_Salao_de_Beleza.repository.AgendamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoService {

    private final AgendamentoMapper agendamentoMapper;
    private final AgendamentoRepository agendamentoRepository;

    public AgendamentoService(AgendamentoMapper agendamentoMapper, AgendamentoRepository agendamentoRepository) {
        this.agendamentoMapper = agendamentoMapper;
        this.agendamentoRepository = agendamentoRepository;
    }

    public AgendamentoDTO fazerAgendamento(AgendamentoDTO novoAgendamento) {
        AgendamentoModel agendamento = agendamentoMapper.toModel(novoAgendamento);
        return agendamentoMapper.toDto(agendamentoRepository.save(agendamento));
    }

    public List<AgendamentoDTO> listarAgendamentos() {
        List<AgendamentoModel> lista = agendamentoRepository.findAll();
        return lista.stream()
                .map(agendamentoMapper::toDto)
                .toList();
    }

    public AgendamentoDTO listarPorId(Long id){
        return agendamentoRepository.findById(id)
                .map(agendamentoMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Agendamento com ID " + id + " não encontrado."));
    }

    public AgendamentoDTO alterarAgendamento(Long id, AgendamentoDTO agendamentoDto){
        AgendamentoModel agendamentoExistente = agendamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agendamento com ID " + id + " não encontrado."));

        AgendamentoModel agedamentoAtualizado = agendamentoMapper.toModel(agendamentoDto);
        agedamentoAtualizado.setId(id);
        return agendamentoMapper.toDto(agendamentoRepository.save(agedamentoAtualizado));
    }

    public void deletarAgendamento(Long id){
        if(!agendamentoRepository.existsById(id)){
            throw new EntityNotFoundException("Agendamento com ID " + id + " não encontrado.");
        }
        agendamentoRepository.deleteById(id);
    }
}
