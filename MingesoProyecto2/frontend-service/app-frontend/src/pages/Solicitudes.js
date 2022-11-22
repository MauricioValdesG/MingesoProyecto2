import { Container, Row, Form, FormGroup, Col, Card, Button } from 'react-bootstrap';
import { useState } from 'react';
import SolicitudService from '../services/SolicitudService';


const Solicitudes = () => {

    const initialState = {
        rut_empleado: '',
        correo_jefatura: '',
        fecha: ''
    };

    const [solicitudes, setSolicitudes] = useState(initialState);

    const changeRutHandler = event => {
        setSolicitudes({ ...solicitudes, rut_empleado: event.target.value });
        console.log(solicitudes.rut_empleado);
    };

    const changeCorreoHandler = event => {
        setSolicitudes({ ...solicitudes, correo_jefatura: event.target.value });
        console.log(solicitudes.correo_jefatura);
    };

    const changeFechaHandler = event => {
        setSolicitudes({ ...solicitudes, fecha: event.target.value });
        console.log(solicitudes.fecha);
    };

    const setSolicitud = e => {
        e.preventDefault();
        try {
            let solicitud = { rut_empleado: solicitudes.rut_empleado, correo_jefatura: solicitudes.correo_jefatura, fecha: solicitudes.fecha };
            console.log(solicitudes.rut_empleado)
            console.log(solicitudes.correo_jefatura)
            console.log(solicitudes.fecha)
            console.log("solicitud => " + JSON.stringify(solicitud));
            SolicitudService.IngresarSolicitud(solicitud);
            alert("Se ha subido la solicitud");
        } catch (error) {
            console.log(error.message)
        }
    }

    const [inputs, setInputs] = useState({});

return (
    <Container style={{ marginTop: '70px' }}>
        <Card className='text-center'>
            <Row>
                <Col><h1>Subir una solicitud</h1><br></br> </Col>
            </Row>
            <Row>
                <Form className='text-center'>
                    <FormGroup controlId="rut" value={solicitudes.rut_empleado} onChange={changeRutHandler}>
                        <label><h5>Rut del empleado: </h5></label>
                        <br></br>
                        <h5><input placeholder='12.345.678-9' name="rut" clasname="form-control"></input></h5>
                    </FormGroup>
                    <FormGroup controlId="rut" value={solicitudes.correo_jefatura} onChange={changeCorreoHandler}>
                        <label><h5>Correo de jefatura: </h5></label>
                        <br></br>
                        <h5><input placeholder='correoJefatura@gmail.com' name="email" clasname="form-control"></input></h5>
                    </FormGroup>
                    <FormGroup controlId="fecha" value={solicitudes.fecha} onChange={changeFechaHandler}>
                        <label><h5>Fecha a cubrir: </h5></label>
                        <br></br>
                        <h5><input placeholder='YYYY-MM-DD' name="fecha" clasname="form-control"></input></h5>
                    </FormGroup>
                    <div class="btn-group btn-group-toggle" data-toggle="buttons">
                        <Button onClick={setSolicitud} className="btn btn-success btn-default">Guardar</Button>
                        <Button className="btn btn-danger btn-default">Cancelar</Button>
                    </div>
                </Form>
            </Row>
        </Card>
    </Container>
    )
};

export default Solicitudes;