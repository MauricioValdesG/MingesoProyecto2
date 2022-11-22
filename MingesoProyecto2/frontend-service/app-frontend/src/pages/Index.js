import { Col, Container, Row } from "react-bootstrap";
import logo from '../logo.svg';

const Index = () => {
    return (
        <Container style={{display: 'flex', justifyContent: 'center', marginTop: '70px'}}>
            <Row>
                <Col lg="12" sm="4" className="mt-4">
                    <h4 className="text-center">Bienvenido a</h4>
                    <h3 className="text-center">MueblesStgo!</h3>
                </Col>
                <center>
                    <Col lg="12" sm="8">
                        <img src={logo} className="App-logo" alt="logo" />
                    </Col>
                </center>
            </Row>
        </Container>
    )
};

export default Index;