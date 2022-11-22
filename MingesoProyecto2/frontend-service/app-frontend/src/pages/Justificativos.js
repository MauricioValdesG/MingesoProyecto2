import { Container, Row, Form, FormGroup, Col, Card, Button } from 'react-bootstrap';
import { useState } from 'react';
import JustificativoService from '../services/JustificativoService';

const Justificativos = () => {

    const initialState = {
        rut_empleado: '',
        firma: '',
        fecha: ''
    };

    const [justificativos, setjustificativos] = useState(initialState);

    const changeRutHandler = event => {
        setjustificativos({ ...justificativos, rut_empleado: event.target.value });
        console.log(justificativos.rut_empleado);
    };

    const changeFirmaHandler = event => {
        setjustificativos({ ...justificativos, firma: event.target.value });
        console.log(justificativos.firma);
    };

    const changeFechaHandler = event => {
        setjustificativos({ ...justificativos, fecha: event.target.value });
        console.log(justificativos.fecha);
    };

    const setJustificativo = e => {
        e.preventDefault();
        try {
            let justificativo = { rut_empleado: justificativos.rut_empleado, firma: justificativos.firma, fecha: justificativos.fecha };
            console.log(justificativos.rut_empleado)
            console.log(justificativos.firma)
            console.log(justificativos.fecha)
            console.log("justificativo => " + JSON.stringify(justificativo));
            JustificativoService.IngresarJustificativo(justificativo);
            alert("Se ha subido el justificativo");
        } catch (error) {
            console.log(error.message)
        }
    }

    return (
        <Container style={{ marginTop: '70px' }}>
            <Card className='text-center'>
                <Row>
                    <Col><h1>Subir un justificativo</h1><br></br> </Col>
                </Row>
                <Row>
                    <Form className='text-center'>
                        <FormGroup controlId="rut" value={justificativos.rut_empleado} onChange={changeRutHandler}>
                            <label><h5>Rut del empleado: </h5></label>
                            <br></br>
                            <h5><input placeholder='12.345.678-9' name="rut" clasname="form-control"></input></h5>
                        </FormGroup>
                        <FormGroup controlId="firma" value={justificativos.firma} onChange={changeFirmaHandler}>
                            <label><h5>Firma: </h5></label>
                            <br></br>
                            <h5><input placeholder='Firma' name="firma" clasname="form-control"></input></h5>
                        </FormGroup>
                        <FormGroup controlId="fecha" value={justificativos.fecha} onChange={changeFechaHandler}>
                            <label><h5>Fecha a cubrir: </h5></label>
                            <br></br>
                            <h5><input placeholder='YYYY-MM-DD' name="fecha" clasname="form-control"></input></h5>
                        </FormGroup>
                        <div class="btn-group btn-group-toggle" data-toggle="buttons">
                            <Button onClick={setJustificativo} className="btn btn-success btn-default">Guardar</Button>
                            <Button className="btn btn-danger btn-default">Cancelar</Button>
                        </div>
                    </Form>
                </Row>
            </Card>
        </Container>
    )
}

export default Justificativos;