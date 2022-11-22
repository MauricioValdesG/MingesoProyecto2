package mingeso.clockservice.services;

import mingeso.clockservice.entities.ClockEntity;
import mingeso.clockservice.repositories.ClockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.core.io.Resource;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClockService {

    @Autowired
    UploadService uploadService;
    @Autowired
    ClockRepository clockRepository;
    @Autowired
    RestTemplate restTemplate;

    public ClockEntity save(ClockEntity clock){
        return clockRepository.save(clock);
    }

    public List<ClockEntity> getAll(){
        return clockRepository.findAll();
    }
    public ArrayList<ClockEntity> findAllByRut(String rut_empleado){
        return clockRepository.findAllByRut(rut_empleado);
    }
    public ClockEntity obtenerPorId(int id){
        Long idL= Long.valueOf(id);
        return clockRepository.findById(idL).orElse(null);
    }
    public ArrayList<ClockEntity> findAllById(int idEmpleado){
        String rutEmpleado= restTemplate.getForObject("http://employee-service/employee/getRutById/" + idEmpleado, String.class);
        ArrayList<ClockEntity> clockEmpleado= findAllByRut(rutEmpleado);

        return clockEmpleado;
    }

    public void updateClock(String filename) {

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

            Resource data = uploadService.load(filename);
            BufferedReader reader = new BufferedReader(new InputStreamReader(data.getInputStream()));
            String line = reader.readLine();
            while (line != null) {
                String[] values = line.split(";");
                ClockEntity clock = new ClockEntity();
                clock.setFecha(LocalDate.parse(values[0], formatter));
                clock.setHora(LocalTime.parse(values[1], DateTimeFormatter.ofPattern("HH:mm")));
                clock.setRut_empleado(values[2]);

                clockRepository.save(clock);

                line = reader.readLine();
            }
            reader.close();
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
