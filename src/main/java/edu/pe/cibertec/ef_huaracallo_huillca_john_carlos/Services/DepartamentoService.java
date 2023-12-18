package edu.pe.cibertec.ef_huaracallo_huillca_john_carlos.Services;

import edu.pe.cibertec.ef_huaracallo_huillca_john_carlos.Models.Departamento;
import java.util.List;
import java.util.Optional;

import edu.pe.cibertec.ef_huaracallo_huillca_john_carlos.Repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartamentoService implements IDepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Override
    public List<Departamento> findAll() {
        return (List<Departamento>) departamentoRepository.findAll();
    }

    @Override
    public Departamento findById(Long id) {
        Optional<Departamento> departamento = departamentoRepository.findById(id);
        if(departamento.isEmpty()){
            throw new RuntimeException("Departamento no encontrado");
        }
        return departamento.get();
    }

    @Override
    public Departamento save(Departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    @Override
    public void deleteById(Long id) {
        if(!departamentoRepository.existsById(id)){
            throw new RuntimeException("Departamento no encontrado");
        }
        departamentoRepository.deleteById(id);
    }

    @Override
    public Departamento update(Departamento departamento) {
        if(!departamentoRepository.existsById(departamento.getIdDepartamento())){
            throw new RuntimeException("Departamento no encontrado");
        }
        return departamentoRepository.save(departamento);
    }
}
