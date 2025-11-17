package Sistema_Salao_de_Beleza.dto;

import Sistema_Salao_de_Beleza.enums.StatusAgendamento;
import java.time.LocalTime;

public record AgendamentoRequestDTO(
        String data,
        LocalTime horario,
        StatusAgendamento status,
        Long clienteId,
        Long profissionalId,
        Long servicoId
) {}
