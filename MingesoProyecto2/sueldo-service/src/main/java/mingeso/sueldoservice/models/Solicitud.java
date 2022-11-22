package mingeso.sueldoservice.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Solicitud {
    private Long id_request;
    private String rut_empleado;
    private String correo_jefatura;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate fecha;

}

