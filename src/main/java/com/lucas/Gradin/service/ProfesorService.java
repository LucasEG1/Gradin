package com.lucas.Gradin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucas.Gradin.entity.ProfesorEntity;
import com.lucas.Gradin.exception.ResourceNotFoundException;
import com.lucas.Gradin.repository.ProfesorRepository;

@Service
public class ProfesorService {
    
    @Autowired
    private ProfesorRepository profesorRepository;

    public void validate(Long id) {
        if (!profesorRepository.existsById(id)) {
            throw new ResourceNotFoundException("id " + id + " not exist");
        }
    }

    public ProfesorEntity get(Long id) {
        validate(id);
        return profesorRepository.findById(id).get();
    }

    public Long count() {
        return profesorRepository.count();
    }
}
