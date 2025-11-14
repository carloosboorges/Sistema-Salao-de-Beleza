package Sistema_Salao_de_Beleza.mapper;
import Sistema_Salao_de_Beleza.dto.AgendamentoDTO;
import Sistema_Salao_de_Beleza.entity.AgendamentoModel;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface AgendamentoMapper {

    AgendamentoModel toModel(AgendamentoDTO agendamentoDTO);

    AgendamentoDTO toDto(AgendamentoModel agendamentoModel);

}
