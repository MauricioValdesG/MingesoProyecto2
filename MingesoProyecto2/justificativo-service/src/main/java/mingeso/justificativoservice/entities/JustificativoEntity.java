package mingeso.justificativoservice.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "justificativos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JustificativoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_justificativo", nullable = false)
    private Long id_justificativo;

    private String rut_empleado;
    private String firma;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate fecha;
}
