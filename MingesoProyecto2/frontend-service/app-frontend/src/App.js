import './App.css';
import { Routes, Route } from 'react-router-dom';
import { Container } from 'react-bootstrap';
import Layout from './Layout';
import Index from './pages/Index';
import Justificativos from './pages/Justificativos'
import Solicitudes from './pages/Solicitudes'
import ReporteSueldo from './pages/Planilla'
import UploadData from './pages/UploadData';
import Empleados from './pages/Empleado';

function App() {
  return (
    <Layout>
      <Container>
        <Routes>
          <Route path='/' element={<Index />} exact/>
          <Route path='/Empleados' element={<Empleados />} />
          <Route path='/Justificativos' element={<Justificativos/>} exact />
          <Route path='/Solicitudes' element={<Solicitudes/>} exact />
          <Route path='/Planilla' element={<ReporteSueldo/>} exact />
          <Route path='/upload' element={<UploadData />} exact />
        </Routes>
      </Container>
    </Layout>
  );
}

export default App;