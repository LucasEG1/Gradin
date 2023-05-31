package com.lucas.Gradin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.lucas.Gradin.entity.AsignaturaEntity;
import com.lucas.Gradin.exception.ResourceNotFoundException;
import com.lucas.Gradin.repository.AsignaturaRepository;
import com.lucas.Gradin.repository.ProfesorRepository;
import com.lucas.Gradin.helper.ValidationHelper;

@Service
public class AsignaturaService {

    @Autowired
    private AsignaturaRepository oAsignaturaRepository;

    @Autowired
    private ProfesorRepository oProfesorRepository;

    @Autowired
    private AuthService oAuthService;

    // VALIDACIONES
    public void validateAsignaturaId(Long id) {
        if (!oAsignaturaRepository.existsById(id)) {
            throw new ResourceNotFoundException("No existe asignatura con id " + id + ".");
        }
    }
    public void validateProfesorId(Long id) {
        if (!oProfesorRepository.existsById(id)) {
            throw new ResourceNotFoundException("No existe profesor con id " + id + ".");
        }
    }

    public void validateEntity(AsignaturaEntity oAsignaturaEntity) {
        validateProfesorId(oAsignaturaEntity.getProfesor().getId()); // Comprueba que el profesor existe
        ValidationHelper.validateStringLength(oAsignaturaEntity.getNombre(), 1, 50, "El nombre debe tener entre 1 y 50 caracteres.");
        ValidationHelper.validateStringLength(oAsignaturaEntity.getIsbnLibro(), 10, 13, "El ISBN debe tener 10 o 13 caracteres.");
    }

    public AsignaturaEntity getOne(Long id) {
        oAuthService.OnlyOwnerOrSuperuser(id);
        validateAsignaturaId(id);
        return oAsignaturaRepository.findById(id).get();
    }

    public Page<AsignaturaEntity> getPage(Pageable oPageable, String strFilter, Long idProfesor) {
        oAuthService.OnlyOwnerOrSuperuser(idProfesor);

        if ((strFilter == null || strFilter.length()==0) && idProfesor == null) {
            return oAsignaturaRepository.findAll(oPageable);
        } else if (strFilter == null || strFilter.length()==0) {
            return oAsignaturaRepository.findByProfesorId(idProfesor, oPageable);
        } else if (idProfesor == null) {
            return oAsignaturaRepository.findByNombreIgnoreCaseContainingOrIsbnLibroIgnoreCaseContaining(strFilter, strFilter, oPageable);
        } else {
            return oAsignaturaRepository.findByNombreIgnoreCaseContainingOrIsbnLibroIgnoreCaseContainingAndProfesorId(strFilter, strFilter, idProfesor, oPageable);
        }
    }

    public Long create(AsignaturaEntity nuevaAsignaturaEntity) {
        oAuthService.OnlySuperuser();
        nuevaAsignaturaEntity.setId(0L);
        validateEntity(nuevaAsignaturaEntity);
        return oAsignaturaRepository.save(nuevaAsignaturaEntity).getId();
    }

    public AsignaturaEntity update(AsignaturaEntity oAsignaturaEntity) {
        oAuthService.OnlySuperuser();
        validateAsignaturaId(oAsignaturaEntity.getId());
        validateEntity(oAsignaturaEntity);
        return oAsignaturaRepository.save(oAsignaturaEntity);
    }

    public Long count() {
        return oAsignaturaRepository.count();
    }

    public Long delete(Long id) {
        validateAsignaturaId(id);
        oAuthService.OnlySuperuser();
        oAsignaturaRepository.deleteById(id);
        return id;
    }
}
