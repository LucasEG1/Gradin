package com.lucas.Gradin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lucas.Gradin.entity.EscuelaEntity;
import com.lucas.Gradin.exception.ResourceNotFoundException;
import com.lucas.Gradin.repository.EscuelaRepository;

@Service
public class EscuelaService {
    
    @Autowired
    private EscuelaRepository escuelaRepository;

    @Autowired
    private AuthService oAuthService;

    public EscuelaService(EscuelaRepository escuelaRepository) {
        this.escuelaRepository = escuelaRepository;
    }

    public void validate(Long id) {
        if (!escuelaRepository.existsById(id)) {
            throw new ResourceNotFoundException("No existe escuela con id " + id + ".");
        }
    }

    public EscuelaEntity getOne(Long id) {
        oAuthService.OnlySuperuser();
        return escuelaRepository.findById(id).get();
    }

    public Page<EscuelaEntity> getPage(Pageable oPageable, String strFilter){
        oAuthService.OnlySuperuser();
        if (strFilter == null || strFilter.length() == 0) {
            return escuelaRepository.findAll(oPageable);
        } else {
            return escuelaRepository.findByNombreIgnoreCaseContainingOrDireccionIgnoreCaseContainingOrCPIgnoreCaseContaining(oPageable, strFilter, strFilter, strFilter);
        }
    }

    public Long create(EscuelaEntity nuevoEscuelaEntity){
        oAuthService.OnlySuperuser();
        nuevoEscuelaEntity.setId(0L);
        return escuelaRepository.save(nuevoEscuelaEntity).getId();   
    }

    public EscuelaEntity update(EscuelaEntity oEscuelaEntity) {
        oAuthService.OnlySuperuser();
        validate(oEscuelaEntity.getId());
        return escuelaRepository.save(oEscuelaEntity);
    }

    public Long delete(Long id) {
        oAuthService.OnlySuperuser();
        validate(id);
        escuelaRepository.deleteById(id);
        return id;
    }

}
