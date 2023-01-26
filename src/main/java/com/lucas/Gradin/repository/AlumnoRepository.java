package com.lucas.Gradin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lucas.Gradin.entity.AlumnoEntity;

public interface AlumnoRepository extends JpaRepository<AlumnoEntity, Long> {

    Page<AlumnoEntity> findByNombreContainingIgnoreCaseOrApellido1ContainingIgnoreCaseOrApellido2ContainingIgnoreCaseOrEmailContainingIgnoreCaseOrTelefonoContainingIgnoreCase(
            String nombre, String apellido1, String apellido2, String email, String telefono,
            Pageable oPageable);

    Page<AlumnoEntity> findByNombreContainingIgnoreCaseOrApellido1ContainingIgnoreCaseOrApellido2ContainingIgnoreCaseOrEmailContainingIgnoreCaseOrTelefonoContainingIgnoreCaseAndClaseId(
            String strFilter, String strFilter2, String strFilter3, String strFilter4, String strFilter5, Long idClase,
            Pageable oPageable);
    
}
