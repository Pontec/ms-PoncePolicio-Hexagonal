package com.codigo.ms_PoncePolicio_Hexagonal.infraestructure.adapters;

import com.codigo.ms_PoncePolicio_Hexagonal.domain.aggregates.constants.Constants;
import com.codigo.ms_PoncePolicio_Hexagonal.domain.aggregates.dto.EmpleadoDTO;
import com.codigo.ms_PoncePolicio_Hexagonal.domain.aggregates.request.RequestEmpleado;
import com.codigo.ms_PoncePolicio_Hexagonal.domain.aggregates.response.ResponseBase;
import com.codigo.ms_PoncePolicio_Hexagonal.domain.aggregates.response.ResponseReniec;
import com.codigo.ms_PoncePolicio_Hexagonal.domain.ports.out.EmpleadoServiceOut;
import com.codigo.ms_PoncePolicio_Hexagonal.infraestructure.dao.EmpleadoRepository;
import com.codigo.ms_PoncePolicio_Hexagonal.infraestructure.entity.EmpleadoEntity;
import com.codigo.ms_PoncePolicio_Hexagonal.infraestructure.exception.personalizada.EmpleadoException;
import com.codigo.ms_PoncePolicio_Hexagonal.infraestructure.mapper.EmpleadoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmpleadoAdapter implements EmpleadoServiceOut{

    private final EmpleadoRepository empleadoRepository;
    private final EmpleadoMapper empleadoMapper;
    private final RestTemplate restTemplate;

    @Value("${token}")
    private String token;

    @Override
    public ResponseBase crearEmpleadoOut(RequestEmpleado empleado) {
        try {
            EmpleadoEntity empleadoEntity = createEmpleadoEntity(empleado);
            if (empleadoEntity != null){
                EmpleadoDTO empleadoDTO = empleadoMapper.mapToDto(empleadoRepository.save(empleadoEntity));
                return new ResponseBase(Constants.CODIGO_EXITO, Constants.MENSAJE_EXITO, Optional.of(empleadoDTO));
            }else {
                throw new EmpleadoException("Empleado no creado");
            }
        }catch (NullPointerException ex){
            throw new EmpleadoException("Datos invalidos: " + ex.getMessage());
        }
    }

    @Override
    public ResponseBase buscarEmpleadoDniIn(String numDoc) {
        String empleadoDni = empleadoRepository.findByNumDoc(numDoc).getNumDoc();
        if (empleadoDni != null){
            EmpleadoDTO empleadoDTO = empleadoMapper.mapToDto(empleadoRepository.findByNumDoc(numDoc));
            return new ResponseBase(Constants.CODIGO_EXITO, Constants.MENSAJE_BUSCAR_DNI+ numDoc, Optional.of(empleadoDTO));
        }else {
            throw new EmpleadoException("empleado con DNI " + numDoc + " no encontrado");
        }
    }

    @Override
    public ResponseBase buscarEmpleadosActivoOut() {
        List<EmpleadoEntity> empleadoEntities = empleadoRepository.findAllByEstadoIsTrue();
        List<EmpleadoDTO> empleadoDTO = empleadoEntities.stream()
                        .map( empleadoMapper::mapToDto)
                        .collect(Collectors.toList());
        return  new ResponseBase(Constants.CODIGO_EXITO, Constants.MENSAJE_BUSCAR_TODOS, Optional.of(empleadoDTO));
    }

    @Override
    public ResponseBase actualizarEmpleadoOut(String numDoc, RequestEmpleado requestEmpleado) {
        boolean existe = empleadoRepository.existsByNumDoc(numDoc);

        if (existe) {
            EmpleadoEntity empleadoBD = empleadoRepository.findByNumDoc(numDoc);
            if (empleadoBD != null) {
                EmpleadoEntity empleado = updateEmpleadoEntity(requestEmpleado, empleadoBD);
                EmpleadoDTO empleadoDTO = empleadoMapper.mapToDto(empleadoRepository.save(empleado));
                return new ResponseBase(Constants.CODIGO_EXITO, Constants.MENSAJE_EXITO_UPDATE, Optional.of(empleadoDTO));
            } else {
                throw new EmpleadoException("No se pudo actualizar con el DNI " + numDoc);
            }
        } else {
            throw new EmpleadoException("Empleado con DNI " + numDoc + " no existe");
        }
    }

    @Override
    public ResponseBase eliminarEmpleadoOut(String numDoc) {
        EmpleadoEntity empleadoBD= empleadoRepository.findByNumDoc(numDoc);
        if (empleadoBD != null){
            empleadoBD.setEstado(false);
            empleadoBD.setUsuaDelete(Constants.USU_ADMIN);
            empleadoBD.setDateDelete(getTime());
            return new ResponseBase(Constants.CODIGO_EXITO, "Eliminado Correctamente", Optional.of(empleadoRepository.save(empleadoBD)));
        }else {
            throw new EmpleadoException("Empleado con DNI "+ numDoc +  " no existe");
        }
    }

    private EmpleadoEntity createEmpleadoEntity(RequestEmpleado requestEmpleado) {
        EmpleadoEntity empleadoEntity = getEmpleadoRestTemplate(requestEmpleado);
        if (empleadoEntity != null) {
            empleadoEntity.setDireccion(requestEmpleado.getDireccion());
            empleadoEntity.setEdad(requestEmpleado.getEdad());
            empleadoEntity.setCargo(requestEmpleado.getCargo());
            empleadoEntity.setDepartamento(requestEmpleado.getDepartamento());
            empleadoEntity.setSalario(requestEmpleado.getSalario());
            empleadoEntity.setTelefono(requestEmpleado.getTelefono());
            empleadoEntity.setCorreo(requestEmpleado.getCorreo());
            empleadoEntity.setEstado(requestEmpleado.isEstado());
            empleadoEntity.setDataCrea(getTime());
            empleadoEntity.setUsuaCreate(Constants.USU_ADMIN);
            return empleadoEntity;
        } else {
            throw new EmpleadoException("No se puede obtener datos de RENIEC");
        }
    }

    private EmpleadoEntity updateEmpleadoEntity(RequestEmpleado requestEmpleado, EmpleadoEntity empleadoBD) {
        empleadoBD.setEdad(requestEmpleado.getEdad());
        empleadoBD.setCargo(requestEmpleado.getCargo());
        empleadoBD.setSalario(requestEmpleado.getSalario());
        empleadoBD.setTelefono(requestEmpleado.getTelefono());
        empleadoBD.setCorreo(requestEmpleado.getCorreo());
        empleadoBD.setDepartamento(requestEmpleado.getDepartamento());
        empleadoBD.setEstado(requestEmpleado.isEstado());
        empleadoBD.setUsuaUpdate(Constants.USU_ADMIN);
        empleadoBD.setDateUpdate(getTime());
        return empleadoBD;
    }

    private Timestamp getTime(){
        long current = System.currentTimeMillis();
        return new Timestamp(current);
    }

    private EmpleadoEntity getEmpleadoRestTemplate(RequestEmpleado requestEmpleado){
        String url = "https://api.apis.net.pe/v2/reniec/dni?numero=" + requestEmpleado.getNumDoc();

        try {
            ResponseEntity<ResponseReniec> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    new HttpEntity<>(createHeaders(token)),
                    ResponseReniec.class
            );
            ResponseReniec responseReniec = response.getBody();
            EmpleadoEntity empleadoEntity = new EmpleadoEntity();
            empleadoEntity.setNombre(responseReniec.getNombres());
            empleadoEntity.setApellido(responseReniec.getApellidoPaterno() + " " + responseReniec.getApellidoMaterno());
            empleadoEntity.setTipoDoc(responseReniec.getTipoDocumento());
            empleadoEntity.setNumDoc(responseReniec.getNumeroDocumento());
            return empleadoEntity;
        }catch (HttpClientErrorException e){
            System.err.println("ERROR AL CONSUMIR EL API EXTERNA " + e.getMessage());
        }
        return null;
    }

    private HttpHeaders createHeaders(String tokenApin){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        return headers;
    }
}
