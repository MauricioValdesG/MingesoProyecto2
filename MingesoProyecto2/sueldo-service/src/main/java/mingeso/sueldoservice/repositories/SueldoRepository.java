package mingeso.sueldoservice.repositories;

import mingeso.sueldoservice.entities.SueldoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SueldoRepository extends JpaRepository<SueldoEntity, Long> {
}
