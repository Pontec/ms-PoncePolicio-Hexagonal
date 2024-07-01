package com.codigo.ms_PoncePolicio_Hexagonal.domain.ports.out;

import com.codigo.ms_PoncePolicio_Hexagonal.domain.aggregates.request.RequestEmpleado;
import com.codigo.ms_PoncePolicio_Hexagonal.domain.aggregates.response.ResponseBase;

public interface EmpleadoServiceOut {

    ResponseBase crearEmpleadoOut(RequestEmpleado empleado);

    ResponseBase buscarEmpleadoDniIn(String numDoc);
    ResponseBase buscarEmpleadosActivoOut();

    ResponseBase actualizarEmpleadoOut(String numDoc, RequestEmpleado requestEmpleado);

    ResponseBase eliminarEmpleadoOut(String numDoc);

}
