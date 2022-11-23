import axios from 'axios';

class EmpleadoService {
    IngresarEmpleado(empleado) {
        return axios.post('http://localhost:8080/employee/create', empleado)
    }
}

export default new EmpleadoService();