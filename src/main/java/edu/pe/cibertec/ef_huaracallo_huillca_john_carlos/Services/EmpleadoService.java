package edu.pe.cibertec.ef_huaracallo_huillca_john_carlos.Services;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import edu.pe.cibertec.ef_huaracallo_huillca_john_carlos.Models.Empleado;
import edu.pe.cibertec.ef_huaracallo_huillca_john_carlos.Repository.IEmpleadoRepository;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoService implements IEmpleadoService {

    @Autowired
    private IEmpleadoRepository empleadoRepository;

    @Override
    public List<Empleado> findAll() {
        return (List<Empleado>) empleadoRepository.findAll();
    }

    @Override
    public Empleado findById(Long id) {
        Optional<Empleado> empleado = empleadoRepository.findById(id);
        if (empleado.isEmpty()) {
            throw new RuntimeException("Empleado no encontrado");
        }
        return empleado.get();
    }

    @Override
    public Empleado save(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    @Override
    public void deleteById(Long id) {
        if (!empleadoRepository.existsById(id)) {
            throw new RuntimeException("Empleado no encontrado");
        }
        empleadoRepository.deleteById(id);
    }

    @Override
    public Empleado update(Empleado empleado) {
        if (!empleadoRepository.existsById(empleado.getIdEmpleado())) {
            throw new RuntimeException("Empleado no encontrado");
        }
        return empleadoRepository.save(empleado);
    }


    @Override
    public JasperPrint report() {

        JasperPrint jasperPrint = null;
        try {
            InputStream jasperStream = getClass().getResourceAsStream("src/main/resources/reports/Blank_A4.jasper");
            Map<String, Object> parameters = new HashMap<>();
            jasperPrint = JasperFillManager.fillReport(jasperStream, parameters, new JREmptyDataSource());
        } catch (JRException e) {
            e.printStackTrace();
        }
        return jasperPrint;
    }
}

