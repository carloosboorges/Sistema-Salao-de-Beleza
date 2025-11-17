package Sistema_Salao_de_Beleza.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfissionalDTO {

    private Long id;
    private String nome;
    private String especialidade;
    private String telefone;
    private String email;
    private String cpf;
}
