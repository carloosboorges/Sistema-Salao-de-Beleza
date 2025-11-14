package Sistema_Salao_de_Beleza.mapper;
import Sistema_Salao_de_Beleza.dto.ProfissionalDTO;
import Sistema_Salao_de_Beleza.entity.ProfissionalModel;

public interface ProfissionalMapper {

    ProfissionalModel toModel (ProfissionalDTO profissionalDTO);

    ProfissionalDTO toDto (ProfissionalModel profissionalModel);
}
