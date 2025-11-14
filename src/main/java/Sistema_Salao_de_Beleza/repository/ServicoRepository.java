package Sistema_Salao_de_Beleza.repository;
import Sistema_Salao_de_Beleza.entity.ServicoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<ServicoModel, Long> {
}
