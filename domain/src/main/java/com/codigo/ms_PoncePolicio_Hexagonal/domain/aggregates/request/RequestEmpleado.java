package com.codigo.ms_PoncePolicio_Hexagonal.domain.aggregates.request;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestEmpleado {

    private String numDoc;

    private int edad;
    private String cargo;
    private String tipoDoc;
    private String departamento;
    private double salario;
    private String telefono;
    private String correo;
    private boolean estado;
    private String direccion;


}
