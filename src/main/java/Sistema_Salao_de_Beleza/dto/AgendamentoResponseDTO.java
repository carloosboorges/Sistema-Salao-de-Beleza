package Sistema_Salao_de_Beleza.dto;
import Sistema_Salao_de_Beleza.enums.StatusAgendamento;
import java.time.LocalTime;

public record AgendamentoResponseDTO(
        Long id,
        String data,
        LocalTime horario,
        StatusAgendamento status,
        String clienteNome,
        String profissionalNome,
        String servicoNome
) {}
