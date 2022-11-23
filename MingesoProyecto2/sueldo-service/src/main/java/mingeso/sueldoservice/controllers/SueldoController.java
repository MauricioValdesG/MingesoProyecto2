package mingeso.sueldoservice.controllers;

import mingeso.sueldoservice.entities.SueldoEntity;
import mingeso.sueldoservice.models.Employee;
import mingeso.sueldoservice.services.SueldoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sueldo")
public class SueldoController {

    @Autowired
    SueldoService sueldoService;

    @GetMapping("/getAll")
    public ResponseEntity<List<SueldoEntity>> crearPlanilla() {
        List<Employee> employees = sueldoService.obtenerEmpleados();
        Employee employeeActual = new Employee();
        int id_sueldo=0;
        for (int i=0; i < employees.size();i++){
            employeeActual = employees.get(i);
            sueldoService.crearPlanilla(employeeActual, id_sueldo);
            id_sueldo = id_sueldo + 1;
        }
        ArrayList<SueldoEntity> sueldos = sueldoService.obtenerSueldos();
        return ResponseEntity.ok(sueldos);
    }
}
