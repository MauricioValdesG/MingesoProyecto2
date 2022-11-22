import axios from 'axios';

class SolicitudService {
    IngresarSolicitud(solicitud) {
        return axios.post('http://localhost:8080/solicitud/create', solicitud)
    }
}

export default new SolicitudService();