package com.lucas.Gradin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucas.Gradin.entity.ProfesorEntity;

public interface ProfesorRepository extends JpaRepository<ProfesorEntity, Long> {
    
}
