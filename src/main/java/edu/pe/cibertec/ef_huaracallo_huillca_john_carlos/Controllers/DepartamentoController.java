package edu.pe.cibertec.ef_huaracallo_huillca_john_carlos.Controllers;

import java.util.List;

import edu.pe.cibertec.ef_huaracallo_huillca_john_carlos.Models.Departamento;
import edu.pe.cibertec.ef_huaracallo_huillca_john_carlos.Services.IDepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/departamento")
public class DepartamentoController {
    
    @Autowired
    private IDepartamentoService departamentoService;

    @GetMapping("/{id}")
    public ResponseEntity<Departamento> findById(Long id){
        return ResponseEntity.ok(departamentoService.findById(id)) != null ? ResponseEntity.ok(departamentoService.findById(id)) : ResponseEntity.notFound().build();
    }
    
    @GetMapping
    public ResponseEntity<List<Departamento>> findAll(){
        return ResponseEntity.ok(departamentoService.findAll());
    }

    @PostMapping
    public ResponseEntity<Departamento> save (@RequestBody Departamento departamento){
        return new ResponseEntity<>(departamentoService.save(departamento), org.springframework.http.HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Departamento> update(@RequestBody Departamento departamento){
        Departamento departamentoActual = departamentoService.findById(departamento.getIdDepartamento());
        return departamentoActual != null ? ResponseEntity.ok(departamentoService.update(departamento)) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(Long id){
        departamentoService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
