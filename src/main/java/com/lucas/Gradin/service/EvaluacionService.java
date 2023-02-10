package com.lucas.Gradin.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.lucas.Gradin.repository.EscuelaRepository;
import com.lucas.Gradin.entity.EvaluacionEntity;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public class EvaluacionService {
    
    @Autowired
    private EscuelaRepository escuelaRepository;

    @Autowired
    private AuthService oAuthService;

    /* public List<EvaluacionEntity> getEvaluaciones(Pageable oPageable) {
        return escuelaRepository.findAll(oPageable);
    } */

}
