package com.lucas.Gradin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lucas.Gradin.entity.AulaEntity;

public interface AulaRepository extends JpaRepository<AulaEntity, Long> {

    Page<AulaEntity> findByNombreContainingIgnoreCase(String strFilter, Pageable oPageable);

    Page<AulaEntity> findByNombreContainingIgnoreCaseAndEscuelaId(String strFilter, Long idEscuela, Pageable oPageable);

}
