import { Container, Row, Table, Col, Card, Button } from 'react-bootstrap';
import { useState, useEffect } from 'react';
import axios from 'axios';

const ReporteSueldo = () => {

    const [sueldos, setReporteSueldo] = useState([]);

    const getReporte = async () => {
        try {
            let url = 'http://localhost:8080/sueldo/getAll';
            let response = await axios.get(url);
            if (response.status === 200) {
                setReporteSueldo(response.data);
            }
        } catch (error) {
            console.log(error.message)
        }
    };

    useEffect(() => {
        getReporte();
    }, []);

    return (
        <Container style={{ marginTop: '70px' }}>
            <Card className='text-center'>
                <Row>
                    <Col><h1>Planilla de sueldos</h1><br></br> </Col>
                </Row>
                <Row>
                    <Col>
                        <Button onClick={getReporte} className="btn btn-success btn-lg" variant="success">
                            Generar la planilla
                        </Button>
                    </Col>
                </Row>
                <Row>
                    <Col>
                        <Table striped className="mt-4">
                            <thead>
                                <tr>
                                    <th>Rut</th>
                                    <th>Nombre</th>
                                    <th>Categoria</th>
                                    <th>Años de servicio</th>
                                    <th>Sueldo Fijo mensual</th>
                                    <th>Bonificacion por años de servicio</th>
                                    <th>Monto por horas extras</th>
                                    <th>Monto descuentos</th>
                                    <th>Sueldo bruto</th>
                                    <th>Monto por cotizacion previsional</th>
                                    <th>Monto por cotizacion salud</th>
                                    <th>Monto sueldo final</th>
                                </tr>
                            </thead>
                            <tbody>
                                {sueldos.map((sueldo) => (
                                    <tr key={sueldo.id}>
                                        <td>{sueldo.rut_empleado}</td>
                                        <td>{sueldo.nombre_completo}</td>
                                        <td>{sueldo.categoria}</td>
                                        <td>{sueldo.anios_servicio}</td>
                                        <td>{sueldo.sueldo_fijo}</td>
                                        <td>{sueldo.bonificacion_anios}</td>
                                        <td>{sueldo.horas_extras}</td>
                                        <td>{sueldo.descuentos}</td>
                                        <td>{sueldo.sueldo_bruto}</td>
                                        <td>{sueldo.prevision}</td>
                                        <td>{sueldo.salud}</td>
                                        <td>{sueldo.sueldo_final}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </Table>
                    </Col>
                </Row>
            </Card>
        </Container>
    );
};

export default ReporteSueldo;