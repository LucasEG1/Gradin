package com.lucas.Gradin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucas.Gradin.entity.EvaluacionEntity;

public interface EvaluacionRepository extends JpaRepository<EvaluacionEntity, Long> {
    
}
