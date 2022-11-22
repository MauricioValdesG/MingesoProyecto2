package mingeso.sueldoservice.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import mingeso.sueldoservice.entities.SueldoEntity;
import mingeso.sueldoservice.models.Clock;
import mingeso.sueldoservice.models.Employee;
import mingeso.sueldoservice.models.Justificativo;
import mingeso.sueldoservice.models.Solicitud;
import mingeso.sueldoservice.repositories.SueldoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SueldoService {
    @Autowired
    SueldoRepository sueldoRepository;
    @Autowired
    RestTemplate restTemplate;

    public ArrayList<SueldoEntity> obtenerSueldos(){
        return (ArrayList<SueldoEntity>) sueldoRepository.findAll();
    }

    public SueldoEntity guardarSueldo(SueldoEntity sueldo){
        return sueldoRepository.save(sueldo);
    }

    public ArrayList<Employee> obtenerEmpleados(){
        ResponseEntity<Object[]> response = restTemplate.getForEntity("http://employee-service/employee/getAll", Object[].class);
        Object[] records = response.getBody();
        if(records == null){
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return (ArrayList<Employee>) Arrays.stream(records)
                .map(empleado -> mapper.convertValue(empleado, Employee.class))
                .collect(Collectors.toList());
    }
    public ArrayList<Justificativo> justificativoFindById(int id){
        ResponseEntity<Object[]> response  = restTemplate.getForEntity("http://justificativo-service/justificativo/empleado/" + id,  Object[].class);
        Object[] records = response.getBody();
        if(records == null){
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return (ArrayList<Justificativo>) Arrays.stream(records)
                .map(empleado -> mapper.convertValue(empleado, Justificativo.class))
                .collect(Collectors.toList());
    }
    public ArrayList<Solicitud> solicitudFindById(int id){
        ResponseEntity<Object[]> response  = restTemplate.getForEntity("http://solicitud-service/solicitud/empleado/" + id, Object[].class);
        Object[] records = response.getBody();
        if(records == null){
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return (ArrayList<Solicitud>) Arrays.stream(records)
                .map(empleado -> mapper.convertValue(empleado, Solicitud.class))
                .collect(Collectors.toList());
    }
    public ArrayList<Clock> clockFindById(int id) {
        ResponseEntity<Object[]> response = restTemplate.getForEntity("http://clock-service/clocks/empleado/" + id, Object[].class);
        Object[] records = response.getBody();
        if (records == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return (ArrayList<Clock>) Arrays.stream(records)
                .map(empleado -> mapper.convertValue(empleado, Clock.class))
                .collect(Collectors.toList());
    }
    public double sueldoFijo(Employee employee){
        double sueldoFijo = 0;

        if (employee.getCategoria().equals("A")){
            sueldoFijo = 1700000;
        }
        if (employee.getCategoria().equals("B")){
            sueldoFijo = 1200000;
        }
        if (employee.getCategoria().equals("C")){
            sueldoFijo = 800000;
        }
        return sueldoFijo;
    }

    public double aniosServicio(Employee employee){
        double anios;
        LocalDate fIni = employee.getFecha_ing();

        LocalDate fFin = LocalDate.now();

        Period pp = Period.between(fIni, fFin);

        anios = pp.getYears();
        return anios;
    }

    public int bonificacionAniosServicio(Employee employee){
        double bonificacion = 0;

        double anios = aniosServicio(employee);

        if (anios < 5){
            bonificacion = sueldoFijo(employee) * 0.0;
        }
        if (anios >= 5 && anios < 10){
            bonificacion = sueldoFijo(employee) * 0.05;
        }
        if (anios >= 10 && anios < 15){
            bonificacion = sueldoFijo(employee) * 0.08;
        }
        if (anios >= 15 && anios < 20){
            bonificacion = sueldoFijo(employee) * 0.11;
        }
        if (anios >= 20 && anios < 25){
            bonificacion = sueldoFijo(employee) * 0.14;
        }
        if (anios >= 25){
            bonificacion = sueldoFijo(employee) * 0.17;
        }
        return (int)bonificacion;
    }

    public double bonificacionHorasExtras(Employee employee, ArrayList<Clock> clockEmpleado, ArrayList<Solicitud> requestEmpleado){
        double bonificacion = 0;
        int horas = 0;
        LocalDate fecha;
        Clock marcasEmployeer = null;
        LocalTime horaSalida = LocalTime.of(18, 0, 0);
        Duration duration = null;
        for (int i = 0; i < requestEmpleado.size(); i++){
            fecha = requestEmpleado.get(i).getFecha();
            for (int j = 0; j < clockEmpleado.size()-1; j++){
                if (clockEmpleado.get(j).getFecha().isEqual(fecha)){
                    if (clockEmpleado.get(j+1).getFecha().isEqual(fecha)){
                        marcasEmployeer = clockEmpleado.get(j+1);
                    }
                }
            }
            if (marcasEmployeer != null) {
                duration = Duration.between(horaSalida, marcasEmployeer.getHora());
            }
            if (duration != null) {
                horas = horas+(int)(duration.toMinutes()/60);
            }
        }
        if (employee.getCategoria().equals("A")){
            bonificacion = horas * 25000;
        }
        if (employee.getCategoria().equals("B")){
            bonificacion = horas * 20000;
        }
        if (employee.getCategoria().equals("C")){
            bonificacion = horas * 10000;
        }
        return bonificacion;
    }

    public double descuentoAtrasos(Employee employee, ArrayList<Clock> clockEmpleado, ArrayList<Justificativo> justificativoEmpleado){
        double descuento = 0;
        int minutosAtraso;
        LocalDate fecha;
        LocalDate fecha2;
        Clock marcasEmployeer;
        LocalTime horaEntrada = LocalTime.of(8, 0, 0);
        Duration duration;
        double sueldo = sueldoFijo(employee);
        int flag = 0;
        for (int i = 0; i < clockEmpleado.size()-1; i++) {
            fecha = clockEmpleado.get(i).getFecha();
            fecha2 = clockEmpleado.get(i+1).getFecha();
            if (fecha.isEqual(fecha2)) {
                marcasEmployeer = clockEmpleado.get(i);
                duration = Duration.between(horaEntrada, marcasEmployeer.getHora());
                minutosAtraso = (int) (duration.getSeconds() / 60);
                if (minutosAtraso >= 70) {
                    for (int j = 0; j < justificativoEmpleado.size(); j++){
                        if (justificativoEmpleado.get(j).getFecha().isEqual(fecha)){
                            flag = 1;
                        }
                    }
                    if (flag != 1){
                        descuento = descuento + (sueldo * 0.15);
                    }
                } else if (minutosAtraso >= 45) {
                    descuento = descuento + (sueldo * 0.06);
                } else if (minutosAtraso >= 25) {
                    descuento = descuento + (sueldo * 0.03);
                } else if (minutosAtraso >= 10) {
                    descuento = descuento + (sueldo * 0.01);
                }
            }
        }
        return descuento;
    }

    public double calcularSueldoBruto(Employee employee, ArrayList<Clock> clockEmpleado, ArrayList<Solicitud> solicitudEmpleado, ArrayList<Justificativo> justificativoEmpleado){
        double sueldoBruto;

        sueldoBruto = sueldoFijo(employee)
                + bonificacionAniosServicio(employee)
                + bonificacionHorasExtras(employee, clockEmpleado, solicitudEmpleado)
                - descuentoAtrasos(employee, clockEmpleado, justificativoEmpleado);

        return sueldoBruto;
    }

    public double cotizacionPrevisional(Employee employee, ArrayList<Clock> clockEmpleado, ArrayList<Solicitud> solicitudEmpleado, ArrayList<Justificativo> justificativoEmpleado){
        double previsional;
        double sueldoBruto = calcularSueldoBruto(employee, clockEmpleado, solicitudEmpleado, justificativoEmpleado);
        previsional = sueldoBruto * 0.1;
        return previsional;
    }

    public double cotizacionSalud(Employee employee, ArrayList<Clock> clockEmpleado, ArrayList<Solicitud> solicitudEmpleado, ArrayList<Justificativo> justificativoEmpleado){
        double salud;
        double sueldoBruto = calcularSueldoBruto(employee, clockEmpleado, solicitudEmpleado, justificativoEmpleado);
        salud = sueldoBruto * 0.08;
        return salud;
    }

    public double sueldoFinal(Employee employee, ArrayList<Clock> clockEmpleado, ArrayList<Solicitud> solicitudEmpleado, ArrayList<Justificativo> justificativoEmpleado){
        double sueldoFinal;
        double sueldoBruto = calcularSueldoBruto(employee, clockEmpleado, solicitudEmpleado, justificativoEmpleado);
        double prevision = cotizacionPrevisional(employee, clockEmpleado, solicitudEmpleado, justificativoEmpleado);
        double salud = cotizacionSalud(employee, clockEmpleado, solicitudEmpleado, justificativoEmpleado);
        sueldoFinal = sueldoBruto - prevision - salud;
        return sueldoFinal;
    }

    public void crearPlanilla(Employee employee, int id){
        ArrayList<Clock> clockEmpleado = clockFindById(id+1);
        ArrayList<Solicitud> solicitudEmpleado = solicitudFindById(id+1);
        ArrayList<Justificativo> justificativoEmpleado = justificativoFindById(id+1);
        SueldoEntity newSueldo;
        String rut = employee.getRut();
        String nombreCompleto = employee.getApellidos()+" "+employee.getNombres();
        String categoria = employee.getCategoria();
        int aniosServicio = (int)aniosServicio(employee);
        int sueldoFijo = (int)sueldoFijo(employee);
        int bonificacionAnios = bonificacionAniosServicio(employee);
        int horasExtra = (int)bonificacionHorasExtras(employee, clockEmpleado, solicitudEmpleado);
        int descuentos = (int)descuentoAtrasos(employee, clockEmpleado, justificativoEmpleado);
        int sueldoBruto = (int)calcularSueldoBruto(employee, clockEmpleado, solicitudEmpleado, justificativoEmpleado);
        int prevision = (int)cotizacionPrevisional(employee, clockEmpleado, solicitudEmpleado, justificativoEmpleado);
        int salud = (int)cotizacionSalud(employee, clockEmpleado, solicitudEmpleado, justificativoEmpleado);
        int sueldoFinal = (int)sueldoFinal(employee, clockEmpleado, solicitudEmpleado, justificativoEmpleado);
        newSueldo = sueldoRepository.save(new SueldoEntity((long)id, rut, nombreCompleto, categoria, aniosServicio, sueldoFijo, bonificacionAnios, horasExtra, descuentos, sueldoBruto, prevision, salud, sueldoFinal));
        guardarSueldo(newSueldo);
    }
}
