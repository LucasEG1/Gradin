package com.lucas.Gradin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucas.Gradin.entity.NotaEntity;

public interface NotaRepository extends JpaRepository<NotaEntity, Long> {
    
}
