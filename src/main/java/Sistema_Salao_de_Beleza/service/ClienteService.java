package Sistema_Salao_de_Beleza.service;
import Sistema_Salao_de_Beleza.dto.ClienteDTO;
import Sistema_Salao_de_Beleza.entity.ClienteModel;
import Sistema_Salao_de_Beleza.mapper.ClienteMapper;
import Sistema_Salao_de_Beleza.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteService {

   private final ClienteRepository clienteRepository;
   private final ClienteMapper clienteMapper;

    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    public ClienteDTO adicionarCliente (ClienteDTO novoClienteDTO){
        ClienteModel cliente = clienteMapper.toModel(novoClienteDTO);
        return clienteMapper.toDto(clienteRepository.save(cliente));
    }

    public List<ClienteDTO> listarClientes(){
        List<ClienteModel> lista = clienteRepository.findAll();
        return lista.stream()
                .map(clienteMapper::toDto)
                .toList();
    }

    public ClienteDTO listarPorId(Long id){
         return clienteRepository.findById(id)
                 .map(clienteMapper::toDto)
                .orElseThrow(()-> new EntityNotFoundException("Cliente com ID " + id + " não encontrado."));
    }

    public ClienteDTO alterarCliente (Long id, ClienteDTO clienteDTO){
        ClienteModel clienteEncontrado = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente com ID " + id + " não encontrado."));

        ClienteModel clienteAtualizado = clienteMapper.toModel(clienteDTO);
        clienteAtualizado.setId(id);
        return clienteMapper.toDto(clienteRepository.save(clienteAtualizado));
    }

    public void deletarCliente(Long id){
        if (!clienteRepository.existsById(id)){
            throw new EntityNotFoundException("Cliente com ID " + id + " não encontrado.");
        }
        clienteRepository.findById(id);
    }
}
