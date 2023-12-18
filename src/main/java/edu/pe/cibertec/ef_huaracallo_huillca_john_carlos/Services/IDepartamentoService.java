package edu.pe.cibertec.ef_huaracallo_huillca_john_carlos.Services;

import edu.pe.cibertec.ef_huaracallo_huillca_john_carlos.Models.Departamento;

import java.util.List;

public interface IDepartamentoService {
    public List<Departamento> findAll();
    public Departamento findById(Long id);
    public Departamento save(Departamento departamento);
    public void deleteById(Long id);
    public Departamento update(Departamento departamento);
}
