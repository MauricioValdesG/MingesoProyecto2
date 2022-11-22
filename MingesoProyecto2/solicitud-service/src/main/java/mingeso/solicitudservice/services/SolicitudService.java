package mingeso.solicitudservice.services;

import mingeso.solicitudservice.entities.SolicitudEntity;
import mingeso.solicitudservice.models.EmployeeModel;
import mingeso.solicitudservice.repositories.SolicitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class SolicitudService {
    @Autowired
    SolicitudRepository solicitudRepository;
    @Autowired
    RestTemplate restTemplate;
    public SolicitudEntity guardarSolicitud(SolicitudEntity solicitud){
        return solicitudRepository.save(solicitud);
    }

    public List<SolicitudEntity> obtenerSolicitudes(){
        return solicitudRepository.findAll();
    }

    public SolicitudEntity guardarSolicitudes(SolicitudEntity solicitud) {
        return solicitudRepository.save(solicitud);
    }

    public ArrayList<SolicitudEntity> findAllByRut(String rut_empleado){
        return solicitudRepository.findAllByRut(rut_empleado);
    }

    public ArrayList<SolicitudEntity> findAllById(int idEmpleado){
        String rutEmpleado= restTemplate.getForObject("http://employee-service/employee/getRutById/" + idEmpleado, String.class);
        ArrayList<SolicitudEntity> solicitudesEmpleado = findAllByRut(rutEmpleado);

        return solicitudesEmpleado;
    }

}