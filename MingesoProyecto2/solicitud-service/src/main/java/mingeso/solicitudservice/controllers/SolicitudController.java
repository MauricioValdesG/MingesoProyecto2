package mingeso.solicitudservice.controllers;

import mingeso.solicitudservice.entities.SolicitudEntity;
import mingeso.solicitudservice.repositories.SolicitudRepository;
import mingeso.solicitudservice.services.SolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/solicitud")
public class SolicitudController {
    @Autowired
    private SolicitudService solicitudService;
    @Autowired
    private SolicitudRepository solicitudRepository;

    @PostMapping("/create")
    public ResponseEntity<SolicitudEntity> subirJustificativo(@RequestBody SolicitudEntity solicitud) {
        ArrayList<SolicitudEntity> solicitudes = solicitudRepository.findAllByRut(solicitud.getRut_empleado());
        for(int i = 0; i< solicitudes.size();i++){
            if(solicitudes.get(i).getFecha().equals(solicitud.getFecha())){
                return ResponseEntity.badRequest().build();
            }
        }
        if(solicitud == null){
            return ResponseEntity.badRequest().build();
        }
        else {
            SolicitudEntity solicitudFinal  = solicitudService.guardarSolicitud(solicitud);
            return ResponseEntity.ok(solicitud);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<SolicitudEntity>> obtenerSolicitudes(){
        List<SolicitudEntity> solicitudes = solicitudService.obtenerSolicitudes();
        if(solicitudes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.ok(solicitudes);
        }
    }

    @GetMapping("/empleado/{id}")
    public ResponseEntity<List<SolicitudEntity>> getById(@PathVariable("id") int id_empleado) {

        List<SolicitudEntity> solicitudesEmpleado= solicitudService.findAllById(id_empleado);

        if(solicitudesEmpleado.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(solicitudesEmpleado);
    }

}
