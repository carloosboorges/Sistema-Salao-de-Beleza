package Sistema_Salao_de_Beleza.dto;

import java.math.BigDecimal;


public record ServicoDTO(

        Long id,
        String nome,
        Integer duracao,
        BigDecimal preco

) {
}
