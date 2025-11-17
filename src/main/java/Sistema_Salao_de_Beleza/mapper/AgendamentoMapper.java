package Sistema_Salao_de_Beleza.mapper;
import Sistema_Salao_de_Beleza.dto.AgendamentoResponseDTO;
import Sistema_Salao_de_Beleza.entity.AgendamentoModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface AgendamentoMapper {

    // Model → DTO (listar)
    @Mapping(source = "cliente.nome",      target = "clienteNome")
    @Mapping(source = "profissional.nome", target = "profissionalNome")
    @Mapping(source = "servico.nome",      target = "servicoNome")
    AgendamentoResponseDTO toResponse(AgendamentoModel agendamentoModel);

}
