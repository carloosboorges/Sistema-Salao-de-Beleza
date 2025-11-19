package Sistema_Salao_de_Beleza.service;

import Sistema_Salao_de_Beleza.dto.AgendamentoRequestDTO;
import Sistema_Salao_de_Beleza.dto.AgendamentoResponseDTO;
import Sistema_Salao_de_Beleza.entity.AgendamentoModel;
import Sistema_Salao_de_Beleza.mapper.AgendamentoMapper;
import Sistema_Salao_de_Beleza.repository.AgendamentoRepository;
import Sistema_Salao_de_Beleza.repository.ClienteRepository;
import Sistema_Salao_de_Beleza.repository.ProfissionalRepository;
import Sistema_Salao_de_Beleza.repository.ServicoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoService {

    private final AgendamentoMapper agendamentoMapper;
    private final AgendamentoRepository agendamentoRepository;
    private final ProfissionalRepository profissionalRepository;
    private final ServicoRepository servicoRepository;
    private final ClienteRepository clienteRepository;

    public AgendamentoService(
            AgendamentoMapper agendamentoMapper,
            AgendamentoRepository agendamentoRepository,
            ProfissionalRepository profissionalRepository,
            ServicoRepository servicoRepository,
            ClienteRepository clienteRepository) {

        this.agendamentoMapper = agendamentoMapper;
        this.agendamentoRepository = agendamentoRepository;
        this.profissionalRepository = profissionalRepository;
        this.servicoRepository = servicoRepository;
        this.clienteRepository = clienteRepository;
    }

    // =====================================
    //            CRIAR AGENDAMENTO
    // =====================================
    public AgendamentoResponseDTO fazerAgendamento(AgendamentoRequestDTO novoAgendamentoDto) {

        AgendamentoModel model = new AgendamentoModel();

        model.setData(novoAgendamentoDto.data());
        model.setHorario(novoAgendamentoDto.horario());
        model.setStatus(novoAgendamentoDto.status());

        model.setCliente(clienteRepository.findById(novoAgendamentoDto.clienteId())
                        .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"))
        );

        model.setProfissional(profissionalRepository.findById(novoAgendamentoDto.profissionalId())
                        .orElseThrow(() -> new EntityNotFoundException("Profissional não encontrado"))
        );

        model.setServico(servicoRepository.findById(novoAgendamentoDto.servicoId())
                        .orElseThrow(() -> new EntityNotFoundException("Serviço não encontrado"))
        );

        AgendamentoModel salvo = agendamentoRepository.save(model);

        return agendamentoMapper.toResponse(agendamentoRepository.save(salvo));
    }

    // =====================================
    //         LISTAR TODOS
    // =====================================
    public List<AgendamentoResponseDTO> listarAgendamentos() {
        return agendamentoRepository.findAll()
                .stream()
                .map(agendamentoMapper::toResponse)
                .toList();
    }

    // =====================================
    //         LISTAR POR ID
    // =====================================
    public AgendamentoResponseDTO listarPorId(Long id) {
        return agendamentoRepository.findById(id)
                .map(agendamentoMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Agendamento com ID "
                        + id + " não encontrado."));
    }

    // =====================================
    //         ATUALIZAR AGENDAMENTO
    // =====================================
    public AgendamentoResponseDTO alterarAgendamento(Long id, AgendamentoRequestDTO novoAgendamentoDto) {

        AgendamentoModel agendamentoExistente = agendamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agendamento com ID "
                        + id + " não encontrado."));

        agendamentoExistente.setData(novoAgendamentoDto.data());
        agendamentoExistente.setHorario(novoAgendamentoDto.horario());
        agendamentoExistente.setStatus(novoAgendamentoDto.status());

        agendamentoExistente.setCliente(clienteRepository.findById(novoAgendamentoDto.clienteId())
                        .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"))
        );

        agendamentoExistente.setProfissional(profissionalRepository.findById(novoAgendamentoDto.profissionalId())
                        .orElseThrow(() -> new EntityNotFoundException("Profissional não encontrado"))
        );

        agendamentoExistente.setServico(servicoRepository.findById(novoAgendamentoDto.servicoId())
                        .orElseThrow(() -> new EntityNotFoundException("Serviço não encontrado"))
        );

        AgendamentoModel salvo = agendamentoRepository.save(agendamentoExistente);

        return agendamentoMapper.toResponse(agendamentoRepository.save(salvo));
    }

    // =====================================
    //             DELETAR
    // =====================================
    public void deletarAgendamento(Long id) {
        if (!agendamentoRepository.existsById(id)) {
            throw new EntityNotFoundException("Agendamento com ID "
                    + id + " não encontrado.");
        }
        agendamentoRepository.deleteById(id);
    }
}
