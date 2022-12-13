package com.lucas.Gradin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.lucas.Gradin.entity.ProfesorEntity;
import com.lucas.Gradin.exception.ResourceNotFoundException;
import com.lucas.Gradin.repository.ProfesorRepository;

@Service
public class ProfesorService {
    
    @Autowired
    private ProfesorRepository oProfesorRepository;

    public void validate(Long id) {
        if (!oProfesorRepository.existsById(id)) {
            throw new ResourceNotFoundException("No existe el profesor con id " + id + ".");
        }
    }

    public ProfesorEntity getOne(Long id) {
        validate(id);
        return oProfesorRepository.findById(id).get();
    }

    public Page<ProfesorEntity> getPage(Pageable oPageable, String strFilter) {
        //oAuthService.OnlyAdmins();
        
        if (strFilter == null || strFilter.length()==0) {
            return oProfesorRepository.findAll(oPageable);
        } else {
            return oProfesorRepository.findByNombreIgnoreCaseContainingOrApellido1IgnoreCaseContainingOrApellido2IgnoreCaseContaining(strFilter, strFilter, strFilter, oPageable);
        }
    }

    public ProfesorEntity create(ProfesorEntity oProfesorEntity) {
        return oProfesorRepository.save(oProfesorEntity);
    }

    public ProfesorEntity update(ProfesorEntity oProfesorEntity) {
        validate(oProfesorEntity.getId());
        return oProfesorRepository.save(oProfesorEntity);
    }

    public Long count() {
        return oProfesorRepository.count();
    }

    public Long delete(Long id) {
        validate(id);
        oProfesorRepository.deleteById(id);
        return id;
    }
}
