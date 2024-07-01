package com.codigo.ms_PoncePolicio_Hexagonal.domain.ports.in;

import com.codigo.ms_PoncePolicio_Hexagonal.domain.aggregates.request.RequestEmpleado;
import com.codigo.ms_PoncePolicio_Hexagonal.domain.aggregates.response.ResponseBase;
import feign.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public interface EmpleadoServiceIn {

    ResponseBase crearEmpleadoIn(RequestEmpleado empleado);

    ResponseBase buscarEmpleadoDniIn(String numDoc);

    ResponseBase buscarEmpleadosActivoIn();

    ResponseBase actualizarEmpleadoIn(String numDoc, RequestEmpleado requestEmpleado);

    ResponseBase eliminarEmpleadoIn(String numDoc);


}
