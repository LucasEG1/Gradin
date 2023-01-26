package com.lucas.Gradin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lucas.Gradin.entity.ClaseEntity;
import com.lucas.Gradin.exception.ResourceNotFoundException;
import com.lucas.Gradin.repository.ClaseRepository;

@Service
public class ClaseService {
    
    @Autowired
    private ClaseRepository claseRepository;

    @Autowired
    private AuthService oAuthService;

    // VALIDACIONES
    public void validate(Long id) {
        if (!claseRepository.existsById(id)) {
            throw new ResourceNotFoundException("No existe clase con id " + id + ".");
        }
    }

    public ClaseEntity getOne(Long id) {
        oAuthService.OnlyOwnerOrSuperuser(id);
        validate(id);
        return claseRepository.findById(id).get();
    }

    public Page<ClaseEntity> getPage(Pageable oPageable, String strFilter , Long idAula){
        oAuthService.OnlySuperuser();

        if (strFilter == null || strFilter.length() == 0 && idAula == null) {
            return claseRepository.findAll(oPageable);
        } else if (strFilter != null && strFilter.length() > 0 && idAula == null) {
            return claseRepository.findByNombreContainingIgnoreCase(strFilter, oPageable);
        } else {
            return claseRepository.findByNombreContainingIgnoreCaseAndAulaId(strFilter, idAula, oPageable);
        }
    }

    public Long create(ClaseEntity nuevoClaseEntity) {
        oAuthService.OnlySuperuser();
        nuevoClaseEntity.setId(0L);
        return claseRepository.save(nuevoClaseEntity).getId();
    }

    public ClaseEntity update(ClaseEntity oClaseEntity) {
        oAuthService.OnlySuperuser();
        validate(oClaseEntity.getId());
        return claseRepository.save(oClaseEntity);
    }

    public Long delete(Long id) {
        oAuthService.OnlySuperuser();
        validate(id);
        claseRepository.deleteById(id);
        return id;
    }
}
