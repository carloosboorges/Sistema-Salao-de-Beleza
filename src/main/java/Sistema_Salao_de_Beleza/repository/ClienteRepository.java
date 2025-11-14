package Sistema_Salao_de_Beleza.repository;
import Sistema_Salao_de_Beleza.entity.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {
}
