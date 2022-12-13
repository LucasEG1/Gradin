package com.lucas.Gradin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lucas.Gradin.entity.ProfesorEntity;

public interface ProfesorRepository extends JpaRepository<ProfesorEntity, Long> {
    
    Page<ProfesorEntity> findByNombreIgnoreCaseContainingOrApellido1IgnoreCaseContainingOrApellido2IgnoreCaseContaining(String strNombre, String strApellido1, String strApellido2, Pageable oPageable);

}
