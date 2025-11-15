package Sistema_Salao_de_Beleza.mapper;
import Sistema_Salao_de_Beleza.dto.ClienteDTO;
import Sistema_Salao_de_Beleza.entity.ClienteModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteModel toModel(ClienteDTO clienteDTO);

    ClienteDTO toDto(ClienteModel clienteModel);
}
