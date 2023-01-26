package com.lucas.Gradin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lucas.Gradin.entity.EscuelaEntity;

public interface EscuelaRepository extends JpaRepository<EscuelaEntity, Long> {

    Page<EscuelaEntity> findByNombreIgnoreCaseContainingOrDireccionIgnoreCaseContainingOrCPIgnoreCaseContaining(
            Pageable oPageable, String nombre, String direccion, String cpostal);
    
}
