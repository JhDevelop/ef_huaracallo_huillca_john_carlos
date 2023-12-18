package edu.pe.cibertec.ef_huaracallo_huillca_john_carlos.Controllers;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import edu.pe.cibertec.ef_huaracallo_huillca_john_carlos.Models.Empleado;
import edu.pe.cibertec.ef_huaracallo_huillca_john_carlos.Services.IEmpleadoService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperExportManager;

@RestController
@RequestMapping("/api/empleado")
public class EmpleadoController {
    
    @Autowired
    private IEmpleadoService empleadoService;


    @GetMapping("/{id}")
    public ResponseEntity<Empleado> findById(Long id){
        return ResponseEntity.ok(empleadoService.findById(id)) != null ? ResponseEntity.ok(empleadoService.findById(id)) : ResponseEntity.notFound().build();
    }
    
    @GetMapping
    public ResponseEntity<List<Empleado>> findAll(){
        return ResponseEntity.ok(empleadoService.findAll());
    }

    @PostMapping
    public ResponseEntity<Empleado> save(@RequestBody Empleado empleado){
        return new ResponseEntity<>(empleadoService.save(empleado), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleado> update(@RequestBody Empleado empleado){
        Empleado empleadoActual = empleadoService.findById(empleado.getIdEmpleado());
        return empleadoActual != null ? ResponseEntity.ok(empleadoService.update(empleado)) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(Long id){
        empleadoService.deleteById(id);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/reporte")
    public ResponseEntity<byte[]> visualizarReporte01() throws JRException {

        List<Empleado> empleados = empleadoService.findAll();
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(empleados);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("Reporte","REPORTE");
        JasperPrint jasperPrint = JasperFillManager.fillReport("src/main/resources/reports/Empleado.jasper", parameters, dataSource);

        byte[] reporte = JasperExportManager.exportReportToPdf(jasperPrint);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.inline().filename("Empleados.pdf").build());

        return ResponseEntity.ok()
                .headers(headers)
                .body(reporte);

    }


    @GetMapping("/reporte02")
    public ResponseEntity<byte[]> descargarReporte01() throws JRException {
        JasperReport report = JasperCompileManager.compileReport("src/main/resources/reports/Empleado.jrxml");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(empleadoService.findAll());
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("Reporte","REPORTE");
        JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, dataSource);
        byte[] reporte = JasperExportManager.exportReportToPdf(jasperPrint);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.attachment().filename("empleados.pdf").build());

        return ResponseEntity.ok()
                .headers(headers)
                .body(reporte);
    }

}
