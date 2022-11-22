package mingeso.clockservice.repositories;

import mingeso.clockservice.entities.ClockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ClockRepository extends JpaRepository<ClockEntity, Long> {
    @Query(value = "select * from clocks as c where c.rut_empleado = :rut_empleado", nativeQuery = true)
    ArrayList<ClockEntity> findAllByRut(@Param("rut_empleado") String rut_empleado);
}