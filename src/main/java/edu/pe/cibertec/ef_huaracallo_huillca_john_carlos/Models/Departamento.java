package edu.pe.cibertec.ef_huaracallo_huillca_john_carlos.Models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "tbl_departamento")
@Entity
@Getter
@Setter
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDepartamento")
    private Long idDepartamento;

    private String descripcion;
}
