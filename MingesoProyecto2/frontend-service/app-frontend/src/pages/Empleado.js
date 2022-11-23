import { Container, Row, Form, FormGroup, Col, Card, Button } from 'react-bootstrap';
import { useState } from 'react';
import EmpleadoService from '../services/EmpleadoService';

const Empleados = () => {

    const initialState = {
        rut: '',
        apellidos: '',
        nombres: '',
        fecha_nac: '',
        categoria: '',
        fecha_ing: ''
    };

    const [empleados, setempleados] = useState(initialState);

    const changeRutHandler = event => {
        setempleados({ ...empleados, rut: event.target.value });
        console.log(empleados.rut);
    };

    const changeApellidosHandler = event => {
        setempleados({ ...empleados, apellidos: event.target.value });
        console.log(empleados.apellidos);
    };

    const changeNombresHandler = event => {
        setempleados({ ...empleados, nombres: event.target.value });
        console.log(empleados.nombres);
    };

    const changeFecha_nacHandler = event => {
        setempleados({ ...empleados, fecha_nac: event.target.value });
        console.log(empleados.fecha_nac);
    };

    const changeCategoriaHandler = event => {
        setempleados({ ...empleados, categoria: event.target.value });
        console.log(empleados.categoria);
    };

    const changeFecha_ingHandler = event => {
        setempleados({ ...empleados, fecha_ing: event.target.value });
        console.log(empleados.fecha_ing);
    };

    const setEmpleados = e => {
        e.preventDefault();
        try {
            let empleado = { rut: empleados.rut, apellidos: empleados.apellidos, nombres: empleados.nombres, fecha_nac: empleados.fecha_nac, categoria: empleados.categoria, fecha_ing: empleados.fecha_ing };
            console.log(empleados.rut)
            console.log(empleados.apellidos)
            console.log(empleados.nombres)
            console.log(empleados.fecha_nac)
            console.log(empleados.categoria)
            console.log(empleados.fecha_ing)
            console.log("empleado => " + JSON.stringify(empleado));
            EmpleadoService.IngresarEmpleado(empleado);
            alert("Se ha creado el empleado");
        } catch (error) {
            console.log(error.message)
        }
    }

return (
    <Container style={{ marginTop: '70px' }}>
        <Card className='text-center'>
            <Row>
                <Col><h1>Crear un Empleado</h1><br></br> </Col>
            </Row>
            <Row>
                <Form className='text-center'>
                    <FormGroup controlId="rut" value={empleados.rut} onChange={changeRutHandler}>
                        <label><h5>Rut del empleado: </h5></label>
                        <br></br>
                        <h5><input placeholder='12.345.678-9' name="rut" clasname="form-control"></input></h5>
                    </FormGroup>
                    <FormGroup controlId="apellidos" value={empleados.apellidos} onChange={changeApellidosHandler}>
                        <label><h5>Apellido del empleado: </h5></label>
                        <br></br>
                        <h5><input placeholder='llanos' name="apellidos" clasname="form-control"></input></h5>
                    </FormGroup>
                    <FormGroup controlId="nombres" value={empleados.nombres} onChange={changeNombresHandler}>
                        <label><h5>Nombre del empleado: </h5></label>
                        <br></br>
                        <h5><input placeholder='ibai' name="nombre" clasname="form-control"></input></h5>
                    </FormGroup>
                    <FormGroup controlId="fecha_nac" value={empleados.fecha_nac} onChange={changeFecha_nacHandler}>
                        <label><h5>Fecha de nacimiento del empleado: </h5></label>
                        <br></br>
                        <h5><input placeholder='YYYY-MM-DD' name="fecha_nac" clasname="form-control"></input></h5>
                    </FormGroup>
                    <FormGroup controlId="categoria" value={empleados.categoria} onChange={changeCategoriaHandler}>
                        <label><h5>Categoria del empleado: </h5></label>
                        <br></br>
                        <h5><input placeholder='A' name="categoria" clasname="form-control"></input></h5>
                    </FormGroup>
                    <FormGroup controlId="fecha_ing" value={empleados.fecha_ing} onChange={changeFecha_ingHandler}>
                        <label><h5>Fecha de ingreso del empleado: </h5></label>
                        <br></br>
                        <h5><input placeholder='YYYY-MM-DD' name="fecha_ing" clasname="form-control"></input></h5>
                    </FormGroup>
                    <div class="btn-group btn-group-toggle" data-toggle="buttons">
                        <Button onClick={setEmpleados} className="btn btn-success btn-default">Guardar</Button>
                        <Button className="btn btn-danger btn-default">Cancelar</Button>
                    </div>
                </Form>
            </Row>
        </Card>
    </Container>
    )
};

export default Empleados;