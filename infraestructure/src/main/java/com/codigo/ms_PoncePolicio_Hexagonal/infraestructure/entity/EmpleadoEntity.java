package com.codigo.ms_PoncePolicio_Hexagonal.infraestructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "empleado")
public class EmpleadoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "edad")
    private int edad;
    @Column(name = "cargo")
    private String cargo;
    @Column(name = "tipo_documento")
    private String tipoDoc;
    @Column(name = "numero_documento")
    private String numDoc;
    @Column(name = "departamento")
    private String departamento;
    @Column(name = "salario")
    private double salario;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "correo")
    private String correo;
    @Column(name = "estado")
    private boolean estado;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "data_create")
    private Timestamp dataCrea;
    @Column(name = "usua_create")
    private String usuaCreate;
    @Column(name = "date_update")
    private Timestamp dateUpdate;
    @Column(name = "usua_update")
    private String usuaUpdate;
    @Column(name = "date_delete")
    private Timestamp dateDelete;
    @Column(name = "usua_delete")
    private String usuaDelete;
}
