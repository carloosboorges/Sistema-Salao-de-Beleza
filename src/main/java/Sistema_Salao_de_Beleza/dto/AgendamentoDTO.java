package Sistema_Salao_de_Beleza.dto;

import Sistema_Salao_de_Beleza.enums.StatusAgendamento;

import java.time.LocalTime;
public class AgendamentoDTO {

    private Long id;
    private String data;
    private LocalTime horario;
    private StatusAgendamento status;

    // RELACIONAMENTOS — usa ID e não a entidade
    private Long clienteId;
    private Long profissionalId;
    private Long servicoId;

}
