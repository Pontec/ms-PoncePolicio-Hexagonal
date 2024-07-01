package com.codigo.ms_PoncePolicio_Hexagonal.infraestructure.mapper;

import com.codigo.ms_PoncePolicio_Hexagonal.domain.aggregates.dto.EmpleadoDTO;
import com.codigo.ms_PoncePolicio_Hexagonal.infraestructure.entity.EmpleadoEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public EmpleadoDTO mapToDto(EmpleadoEntity empleadoEntity){
        return modelMapper.map(empleadoEntity, EmpleadoDTO.class);
    }

    public EmpleadoEntity mapToEntity(EmpleadoDTO empleadoDTO){
        return modelMapper.map(empleadoDTO, EmpleadoEntity.class);
    }
}
