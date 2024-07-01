package com.codigo.ms_PoncePolicio_Hexagonal.infraestructure.exception.personalizada;

public class EmpleadoException extends RuntimeException{

    public EmpleadoException (String mensaje){
        super(mensaje);
    }
}
