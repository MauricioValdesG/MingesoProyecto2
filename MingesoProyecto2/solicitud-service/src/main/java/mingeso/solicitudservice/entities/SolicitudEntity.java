package mingeso.solicitudservice.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "solicitudes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_request", nullable = false)
    private Long id_request;

    private String rut_empleado;
    private String correo_jefatura;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate fecha;
}
