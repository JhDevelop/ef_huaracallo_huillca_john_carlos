package edu.pe.cibertec.ef_huaracallo_huillca_john_carlos.Services;


import edu.pe.cibertec.ef_huaracallo_huillca_john_carlos.Models.Empleado;
import net.sf.jasperreports.engine.JasperPrint;

import java.util.List;

public interface IEmpleadoService {
    public List<Empleado> findAll();
    Empleado findById(Long id);
    Empleado save(Empleado empleado);
    void deleteById(Long id);
    Empleado update(Empleado empleado);
    public JasperPrint report();
}
