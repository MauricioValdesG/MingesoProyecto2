package mingeso.justificativoservice.services;

import mingeso.justificativoservice.entities.JustificativoEntity;
import mingeso.justificativoservice.models.EmployeeModel;
import mingeso.justificativoservice.repositories.JustificativoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class JustificativoService {
    @Autowired
    JustificativoRepository justificativoRepository;
    @Autowired
    RestTemplate restTemplate;

    public List<JustificativoEntity> obtenerJustificativos(){
        return justificativoRepository.findAll();
    }

    public JustificativoEntity guardarJustificativo(JustificativoEntity justificativo) {
        return justificativoRepository.save(justificativo);
    }

    public ArrayList<JustificativoEntity> findAllByRut(String rut_empleado){
        return justificativoRepository.findAllByRut(rut_empleado);
    }

    public ArrayList<JustificativoEntity> findAllById(int idEmpleado){
        String rutEmpleado= restTemplate.getForObject("http://employee-service/employee/getRutById/" + idEmpleado, String.class);
        ArrayList<JustificativoEntity> justificativosEmpleado= findAllByRut(rutEmpleado);

        return justificativosEmpleado;
    }

}