package com.codigo.ms_PoncePolicio_Hexagonal.infraestructure.dao;

import com.codigo.ms_PoncePolicio_Hexagonal.infraestructure.entity.EmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity,Integer> {

    EmpleadoEntity findByNumDoc(String numDoc);

    List<EmpleadoEntity> findAllByEstadoIsTrue();

    boolean existsByNumDoc(String numDoc);

}
