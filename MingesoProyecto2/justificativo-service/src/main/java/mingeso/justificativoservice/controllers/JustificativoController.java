package mingeso.justificativoservice.controllers;

import mingeso.justificativoservice.entities.JustificativoEntity;
import mingeso.justificativoservice.services.JustificativoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/justificativo")
@CrossOrigin(origins = "*")
public class JustificativoController {
    @Autowired
    private JustificativoService justificativoService;

    @PostMapping("/create")
    public ResponseEntity<JustificativoEntity> crearJustificativo(@RequestBody JustificativoEntity justificativo){
        JustificativoEntity newJustificativo = justificativoService.guardarJustificativo(justificativo);
        return ResponseEntity.ok(newJustificativo);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<JustificativoEntity>> obtenerJustificativos(){
        List<JustificativoEntity> justificativos = justificativoService.obtenerJustificativos();
        if(justificativos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.ok(justificativos);
        }
    }

    @GetMapping("/empleado/{id}")
    public ResponseEntity<List<JustificativoEntity>> getById(@PathVariable("id") int id_empleado) {

        List<JustificativoEntity> justificativosEmpleado= justificativoService.findAllById(id_empleado);

        if(justificativosEmpleado.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(justificativosEmpleado);
    }
}