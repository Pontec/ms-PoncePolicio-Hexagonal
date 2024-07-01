package com.codigo.ms_PoncePolicio_Hexagonal.application.controller;

import com.codigo.ms_PoncePolicio_Hexagonal.domain.aggregates.request.RequestEmpleado;
import com.codigo.ms_PoncePolicio_Hexagonal.domain.aggregates.response.ResponseBase;
import com.codigo.ms_PoncePolicio_Hexagonal.domain.ports.in.EmpleadoServiceIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/empleado")
@RequiredArgsConstructor
public class EmpleadoController {

    private final EmpleadoServiceIn empleadoServiceIn;


    @PostMapping("/crear")
    public ResponseEntity<ResponseBase> creear (@RequestBody RequestEmpleado empleado){
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(empleadoServiceIn.crearEmpleadoIn(empleado));
    }

    @GetMapping("/buscar/{dni}")
    public ResponseEntity<ResponseBase> buscar(@PathVariable String dni){
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(empleadoServiceIn.buscarEmpleadoDniIn(dni));
    }

    @GetMapping("/todos")
    public ResponseEntity<ResponseBase> todos (){
        ResponseBase responseBase = empleadoServiceIn.buscarEmpleadosActivoIn();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseBase);
    }

    @PutMapping("/actualizar/{numDoc}")
    public ResponseEntity actualizar (@PathVariable String numDoc, @RequestBody RequestEmpleado requestEmpleado){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(empleadoServiceIn.actualizarEmpleadoIn(numDoc, requestEmpleado));
    }

    @DeleteMapping("/eliminar/{numDoc}")
    public ResponseEntity borrar (@PathVariable String numDoc){
        ResponseBase empleado = empleadoServiceIn.eliminarEmpleadoIn(numDoc);
        return ResponseEntity.ok(empleado);
    }





}
