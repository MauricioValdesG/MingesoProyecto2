package mingeso.employeeservice.repositories;

import mingeso.employeeservice.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    @Query(value = "select rut from employees as e where e.id_empleado = :id_empleado", nativeQuery = true)
    String findRutById(@Param("id_empleado") int id_empleado);
}
