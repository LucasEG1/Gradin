package com.lucas.Gradin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lucas.Gradin.entity.AulaEntity;
import com.lucas.Gradin.exception.ResourceNotFoundException;
import com.lucas.Gradin.repository.AulaRepository;

@Service
public class AulaService {
    

    @Autowired
    private AulaRepository aulaRepository;

    @Autowired
    private AuthService oAuthService;

    // VALIDACIONES
    public void validate(Long id) {
        if (!aulaRepository.existsById(id)) {
            throw new ResourceNotFoundException("No existe aula con id " + id + ".");
        }
    }

    public AulaEntity getOne(Long id) {
        oAuthService.OnlyOwnerOrSuperuser(id);
        validate(id);
        return aulaRepository.findById(id).get();
    }

    public Page<AulaEntity> getPage (Pageable oPageable, String strFilter, Long idEscuela) {

        if (strFilter == null || strFilter.length() == 0 && idEscuela == null) {
            return aulaRepository.findAll(oPageable);
        } else if (strFilter != null && strFilter.length() > 0 && idEscuela == null) {
            return aulaRepository.findByNombreContainingIgnoreCase(strFilter, oPageable);
        } else {
            return aulaRepository.findByNombreContainingIgnoreCaseAndEscuelaId(strFilter, idEscuela, oPageable);
        }
    }

    public Long create(AulaEntity nuevoAulaEntity) {
        oAuthService.OnlySuperuser();
        nuevoAulaEntity.setId(0L);
        return aulaRepository.save(nuevoAulaEntity).getId();
    }

    public AulaEntity update(AulaEntity oAulaEntity) {
        oAuthService.OnlySuperuser();
        validate(oAulaEntity.getId());
        return aulaRepository.save(oAulaEntity);
    }

    public Long delete(Long id) {
        oAuthService.OnlySuperuser();
        validate(id);
        aulaRepository.deleteById(id);
        return id;
    }
    
}
