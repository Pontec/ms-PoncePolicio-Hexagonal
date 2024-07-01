package com.codigo.ms_PoncePolicio_Hexagonal.domain.impl;

import com.codigo.ms_PoncePolicio_Hexagonal.domain.aggregates.request.RequestEmpleado;
import com.codigo.ms_PoncePolicio_Hexagonal.domain.aggregates.response.ResponseBase;
import com.codigo.ms_PoncePolicio_Hexagonal.domain.ports.in.EmpleadoServiceIn;
import com.codigo.ms_PoncePolicio_Hexagonal.domain.ports.out.EmpleadoServiceOut;
import org.springframework.stereotype.Component;

@Component
public class EmpleadoServiceImpl implements EmpleadoServiceIn {

    private final EmpleadoServiceOut empleadoServiceOut;

    public EmpleadoServiceImpl(EmpleadoServiceOut empleadoServiceOut) {
        this.empleadoServiceOut = empleadoServiceOut;
    }

    @Override
    public ResponseBase crearEmpleadoIn(RequestEmpleado empleado) {
        return empleadoServiceOut.crearEmpleadoOut(empleado);
    }

    @Override
    public ResponseBase buscarEmpleadoDniIn(String numDoc) {
        return empleadoServiceOut.buscarEmpleadoDniIn(numDoc);
    }

    @Override
    public ResponseBase buscarEmpleadosActivoIn() {
        return empleadoServiceOut.buscarEmpleadosActivoOut();
    }

    @Override
    public ResponseBase actualizarEmpleadoIn(String numDoc, RequestEmpleado requestEmpleado) {
        return empleadoServiceOut.actualizarEmpleadoOut(numDoc, requestEmpleado);
    }

    @Override
    public ResponseBase eliminarEmpleadoIn(String numDoc) {
        return empleadoServiceOut.eliminarEmpleadoOut(numDoc);
    }
}
