package com.lucas.Gradin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucas.Gradin.entity.AlumnoEntity;

public interface AlumnoRepository extends JpaRepository<AlumnoEntity, Long> {
    
}
