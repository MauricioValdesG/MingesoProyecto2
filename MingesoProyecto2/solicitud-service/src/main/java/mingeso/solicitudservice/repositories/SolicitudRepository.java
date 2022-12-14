package mingeso.solicitudservice.repositories;


import mingeso.solicitudservice.entities.SolicitudEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface SolicitudRepository extends JpaRepository<SolicitudEntity, Long> {

    @Query(value = "select * from solicitudes as r where r.rut_empleado = :rut_empleado", nativeQuery = true)
    ArrayList<SolicitudEntity> findAllByRut(@Param("rut_empleado") String rut_empleado);
}
