package com.lucas.Gradin.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.lucas.Gradin.repository.EvaluacionRepository;
import com.lucas.Gradin.entity.EvaluacionEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class EvaluacionService {
    
    @Autowired
    private EvaluacionRepository evaluacionRepository;

    public Page<EvaluacionEntity> getEvaluaciones(Pageable oPageable) {
        return evaluacionRepository.findAll(oPageable);
    }
}
