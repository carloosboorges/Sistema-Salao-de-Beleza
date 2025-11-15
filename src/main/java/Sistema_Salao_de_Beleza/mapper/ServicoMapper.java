package Sistema_Salao_de_Beleza.mapper;
import Sistema_Salao_de_Beleza.dto.ServicoDTO;
import Sistema_Salao_de_Beleza.entity.ServicoModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServicoMapper {

    ServicoModel toModel (ServicoDTO servicoDTO);

    ServicoDTO toDto (ServicoModel servicoModel);
}
