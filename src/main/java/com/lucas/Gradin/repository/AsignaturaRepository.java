package com.lucas.Gradin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lucas.Gradin.entity.AsignaturaEntity;

public interface AsignaturaRepository extends JpaRepository<AsignaturaEntity, Long> {
    // Buscar por nombre o isbn
    Page<AsignaturaEntity> findByNombreIgnoreCaseContainingOrIsbnLibroIgnoreCaseContaining(String strNombre, String strIsbn, Pageable oPageable);
    // Buscar por nombre, isbn o profesor
    Page<AsignaturaEntity> findByNombreIgnoreCaseContainingOrIsbnLibroIgnoreCaseContainingAndProfesorId(String strNombre, String strIsbn, Long idProfesor, Pageable oPageable);

    Page<AsignaturaEntity> findByProfesorId(Long idProfesor, Pageable oPageable);
}
