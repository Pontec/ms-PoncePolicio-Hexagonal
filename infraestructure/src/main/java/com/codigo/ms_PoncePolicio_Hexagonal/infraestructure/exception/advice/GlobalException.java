package com.codigo.ms_PoncePolicio_Hexagonal.infraestructure.exception.advice;

import com.codigo.ms_PoncePolicio_Hexagonal.domain.aggregates.response.ResponseBase;
import com.codigo.ms_PoncePolicio_Hexagonal.infraestructure.exception.personalizada.EmpleadoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.util.Optional;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseBase> generalException(Exception ex){
        ResponseBase responseBase = new ResponseBase(500, "ERROR INTERNO: ", Optional.empty());
        return new ResponseEntity<>(responseBase, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ResponseBase> nullPointerException (NullPointerException ex){
        ResponseBase responseBase=new ResponseBase(409, "CONFLICTO INTERNO: ", Optional.empty());
        return new ResponseEntity<>(responseBase, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ResponseBase> ioException (IOException ex){
        ResponseBase responseBase = new ResponseBase(406, "NO ACEPTABLE: ", Optional.empty());
        return new ResponseEntity<>(responseBase, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseBase> runTimeException (RuntimeException ex){
        ResponseBase responseBase = new ResponseBase(400, "ERROR EN LA SOLICITUD: ", Optional.empty());
        return new ResponseEntity<>(responseBase, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmpleadoException.class)
    public ResponseEntity<ResponseBase> EmpleadoException (EmpleadoException ex){
        ResponseBase responseBase = new ResponseBase(409, "ERROR EN EL EMPLEADO: " + ex.getMessage(), Optional.empty());
        return new ResponseEntity<>(responseBase, HttpStatus.CONFLICT);
    }
}
