package mingeso.employeeservice.controllers;

import mingeso.employeeservice.entities.EmployeeEntity;
import mingeso.employeeservice.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/getAll")
    public ResponseEntity<ArrayList<EmployeeEntity>> obtenerEmpleados() {
        ArrayList<EmployeeEntity> empleados = employeeService.obtenerEmpleados();
        if(empleados.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.ok(empleados);
        }
    }
    @GetMapping("/getRutById/{id}")
    public ResponseEntity<String> getRutById(@PathVariable("id") int id_empleado) {
        String rut_empleado = employeeService.findRutById(id_empleado);
        if(rut_empleado.equals(null)){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok(rut_empleado);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<EmployeeEntity> save(@RequestBody EmployeeEntity employee){
        EmployeeEntity newEmployee = employeeService.save(employee);
        return ResponseEntity.ok(newEmployee);
    }

}
