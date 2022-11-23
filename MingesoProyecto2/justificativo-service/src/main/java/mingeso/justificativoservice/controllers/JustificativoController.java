package mingeso.justificativoservice.controllers;

import mingeso.justificativoservice.entities.JustificativoEntity;
import mingeso.justificativoservice.repositories.JustificativoRepository;
import mingeso.justificativoservice.services.JustificativoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/justificativo")
public class JustificativoController {
    @Autowired
    private JustificativoService justificativoService;
    @Autowired
    private JustificativoRepository justificativoRepository;

    @PostMapping("/create")
    public ResponseEntity<JustificativoEntity> subirJustificativo(@RequestBody JustificativoEntity justificativo) {
        ArrayList<JustificativoEntity> justificativos = justificativoRepository.findAllByRut(justificativo.getRut_empleado());
        for(int i = 0; i< justificativos.size();i++){
            if(justificativos.get(i).getFecha().equals(justificativo.getFecha())){
                return ResponseEntity.badRequest().build();
            }
        }
        if(justificativo == null){
            return ResponseEntity.badRequest().build();
        }
        else {
            JustificativoEntity justificado = justificativoService.guardarJustificativo(justificativo);
            return ResponseEntity.ok(justificado);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<JustificativoEntity>> obtenerJustificativos(){
        List<JustificativoEntity> justificativos = justificativoService.obtenerJustificativos();
        return ResponseEntity.ok(justificativos);
    }

    @GetMapping("/empleado/{id}")
    public ResponseEntity<List<JustificativoEntity>> getById(@PathVariable("id") int id_empleado) {

        List<JustificativoEntity> justificativosEmpleado= justificativoService.findAllById(id_empleado);
        return ResponseEntity.ok(justificativosEmpleado);
    }
}