package Sistema_Salao_de_Beleza.service;
import Sistema_Salao_de_Beleza.dto.ServicoDTO;
import Sistema_Salao_de_Beleza.entity.ServicoModel;
import Sistema_Salao_de_Beleza.mapper.ServicoMapper;
import Sistema_Salao_de_Beleza.repository.ServicoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoService {

    private final ServicoRepository servicoRepository;
    private final ServicoMapper servicoMapper;


    public ServicoService(ServicoRepository servicoRepository, ServicoMapper servicoMapper) {
        this.servicoRepository = servicoRepository;
        this.servicoMapper = servicoMapper;
    }

    public ServicoDTO adicionarServico(ServicoDTO servicoDTO) {
        ServicoModel novoServico = servicoMapper.toModel(servicoDTO);
        return servicoMapper.toDto(servicoRepository.save(novoServico));
    }

    public List<ServicoDTO> listaDeServico() {
        List<ServicoModel> lista = servicoRepository.findAll();
        return lista.stream()
                .map(servicoMapper::toDto)
                .toList();
    }

    public ServicoDTO listarPorId(Long id) {
        return servicoRepository.findById(id)
                .map(servicoMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Serviço com ID " + id + " não encontrado."));

    }

    public ServicoDTO atualizarServico(Long id, ServicoDTO servicoDTO) {
        ServicoModel servicoEncontrado = servicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Profissional com ID " + id + " não encontrado."));

        ServicoModel servicoatualizado = servicoMapper.toModel(servicoDTO);
        servicoatualizado.setId(id);
        return servicoMapper.toDto(servicoRepository.save(servicoatualizado));
    }

    public void deletarServico(Long id) {
        if (!servicoRepository.existsById(id)) {
            throw new EntityNotFoundException("Profissional com ID " + id + " não encontrado.");
        }
        servicoRepository.deleteById(id);
    }

}