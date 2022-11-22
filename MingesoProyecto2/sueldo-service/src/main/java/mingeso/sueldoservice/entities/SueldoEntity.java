package mingeso.sueldoservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "sueldos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SueldoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_sueldo", nullable = false)
    private Long id_sueldo;

    private String rut_empleado;
    private String nombre_completo;
    private String categoria;
    private int anios_servicio;
    private int sueldo_fijo;
    private int bonificacion_anios;
    private int horas_extras;
    private int descuento;
    private int sueldo_bruto;
    private int prevision;
    private int salud;
    private int sueldo_final;

}

