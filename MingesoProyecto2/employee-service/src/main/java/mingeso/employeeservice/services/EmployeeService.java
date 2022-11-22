package mingeso.employeeservice.services;

import mingeso.employeeservice.entities.EmployeeEntity;
import mingeso.employeeservice.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public String findRutById(int id_empleado){
        return employeeRepository.findRutById(id_empleado);
    }
    public ArrayList<EmployeeEntity> obtenerEmpleados(){
        return (ArrayList<EmployeeEntity>) employeeRepository.findAll();
    }

    public EmployeeEntity save(EmployeeEntity employee){
        return employeeRepository.save(employee);
    }

}
