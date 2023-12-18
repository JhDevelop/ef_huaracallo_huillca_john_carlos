package edu.pe.cibertec.ef_huaracallo_huillca_john_carlos.Models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Table(name = "tbl_empleado")
@Entity
@Getter
@Setter
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEmpleado")
    private Long idEmpleado;
    private String nombre;
    private BigDecimal salario;
    private Date fechaIngreso;

    @ManyToOne
    @JoinColumn(name = "idDepartamento")
    private Departamento departamento;

}
