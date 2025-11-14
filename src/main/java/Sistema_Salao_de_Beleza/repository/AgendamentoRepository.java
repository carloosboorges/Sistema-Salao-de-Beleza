package Sistema_Salao_de_Beleza.repository;
import Sistema_Salao_de_Beleza.entity.AgendamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<AgendamentoModel, Long> {
}
