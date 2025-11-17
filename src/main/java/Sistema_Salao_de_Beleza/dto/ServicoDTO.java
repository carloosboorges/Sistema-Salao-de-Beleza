package Sistema_Salao_de_Beleza.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicoDTO {

    private Long id;
    private String nome;
    private Integer duracao;
    private BigDecimal preco;

}
