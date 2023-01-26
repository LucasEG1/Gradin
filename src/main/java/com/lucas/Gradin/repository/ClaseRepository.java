package com.lucas.Gradin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lucas.Gradin.entity.ClaseEntity;

public interface ClaseRepository extends JpaRepository<ClaseEntity, Long> {

    Page<ClaseEntity> findByNombreContainingIgnoreCase(String strFilter, Pageable oPageable);

    Page<ClaseEntity> findByNombreContainingIgnoreCaseAndAulaId(String strFilter, Long idAula, Pageable oPageable);
    
}
