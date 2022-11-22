package mingeso.clockservice.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "Clocks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClockEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_clock", nullable = false)
    private Long id_clock;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate fecha;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime hora;
    private String rut_empleado;

}
