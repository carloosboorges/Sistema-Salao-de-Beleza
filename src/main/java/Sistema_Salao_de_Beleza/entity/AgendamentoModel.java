package Sistema_Salao_de_Beleza.entity;
import Sistema_Salao_de_Beleza.enums.StatusAgendamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "agendamento_tb")
public class AgendamentoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "data")
    private String data;

    @Column(name = "horario")
    private LocalTime horario;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusAgendamento status;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteModel cliente;

    @ManyToOne
    @JoinColumn(name = "profissional_id")
    private ProfissionalModel profissional;

    @ManyToOne
    @JoinColumn(name = "servico_id")
    private ServicoModel servico;

}
