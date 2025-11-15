package Sistema_Salao_de_Beleza.mapper;
import Sistema_Salao_de_Beleza.dto.ProfissionalDTO;
import Sistema_Salao_de_Beleza.entity.ProfissionalModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfissionalMapper {

    ProfissionalModel toModel (ProfissionalDTO profissionalDTO);

    ProfissionalDTO toDto (ProfissionalModel profissionalModel);
}
