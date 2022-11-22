package mingeso.solicitudservice.controllers;

import mingeso.solicitudservice.entities.SolicitudEntity;
import mingeso.solicitudservice.services.SolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/solicitud")
@CrossOrigin(origins = "*")
public class SolicitudController {
    @Autowired
    private SolicitudService solicitudService;

    @PostMapping("/create")
    public ResponseEntity<SolicitudEntity> crearSolicitud(@RequestBody SolicitudEntity solicitud){
        SolicitudEntity newSolicitud = solicitudService.guardarSolicitud(solicitud);
        return ResponseEntity.ok(newSolicitud);
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
