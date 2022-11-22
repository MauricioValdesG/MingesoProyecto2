package mingeso.clockservice.controllers;

import mingeso.clockservice.entities.ClockEntity;
import mingeso.clockservice.services.ClockService;
import mingeso.clockservice.services.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/clocks")
@CrossOrigin(origins = "*")
public class ClockController {

    @Autowired
    UploadService uploadService;
    @Autowired
    ClockService clockService;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        try {
            String filename = file.getOriginalFilename();
            uploadService.save(file);
            clockService.updateClock(filename);
            return ResponseEntity.ok().body("File uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<ClockEntity>> getAll(){
        List<ClockEntity> marcas= clockService.getAll();
        if(marcas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(marcas);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ClockEntity> getById(@PathVariable("id") int id) {
        ClockEntity marca = clockService.obtenerPorId(id);
        if(marca == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(marca);
    }

    @GetMapping("/empleado/{id}")
    public ResponseEntity<List<ClockEntity>> getByIdEmpleado(@PathVariable("id") int id) {
        List<ClockEntity> marcas= clockService.findAllById(id);
        if(marcas.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(marcas);
    }
}
