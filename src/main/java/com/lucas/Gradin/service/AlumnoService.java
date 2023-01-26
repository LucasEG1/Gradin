package com.lucas.Gradin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lucas.Gradin.entity.AlumnoEntity;
import com.lucas.Gradin.exception.ResourceNotFoundException;
import com.lucas.Gradin.repository.AlumnoRepository;

@Service
public class AlumnoService {
    
    @Autowired
    private AlumnoRepository alumnoRepository;

    public AlumnoService(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    @Autowired
    private AuthService oAuthService;

    // VALIDACIONES
    public void validate(Long id) {
        if (!alumnoRepository.existsById(id)) {
            throw new ResourceNotFoundException("No existe alumno con id " + id + ".");
        }
    }

    public AlumnoEntity getOne(Long id) {
        oAuthService.OnlyOwnerOrSuperuser(id);
        validate(id);
        return alumnoRepository.findById(id).get();
    }

    public Page<AlumnoEntity> getPage(Pageable oPageable, Long idClase, String strFilter) {
        oAuthService.OnlySuperuser();

        if (strFilter == null || strFilter.length() == 0 && idClase == null) {
            return alumnoRepository.findAll(oPageable);
        } else if (strFilter != null && strFilter.length() > 0 && idClase == null) {
            return alumnoRepository.findByNombreContainingIgnoreCaseOrApellido1ContainingIgnoreCaseOrApellido2ContainingIgnoreCaseOrEmailContainingIgnoreCaseOrTelefonoContainingIgnoreCase(strFilter, strFilter, strFilter, strFilter, strFilter, oPageable);
        } else {
            return alumnoRepository.findByNombreContainingIgnoreCaseOrApellido1ContainingIgnoreCaseOrApellido2ContainingIgnoreCaseOrEmailContainingIgnoreCaseOrTelefonoContainingIgnoreCaseAndClaseId(strFilter, strFilter, strFilter, strFilter, strFilter, idClase, oPageable);
        }
    }

    public Long create(AlumnoEntity nuevoAlumnoEntity) {
        oAuthService.OnlySuperuser();
        nuevoAlumnoEntity.setId(0L);
        return alumnoRepository.save(nuevoAlumnoEntity).getId();
    }

    public AlumnoEntity update(AlumnoEntity oAlumnoEntity) {
        oAuthService.OnlySuperuser();
        validate(oAlumnoEntity.getId());
        return alumnoRepository.save(oAlumnoEntity);
    }

    public Long delete(Long id) {
        oAuthService.OnlySuperuser();
        validate(id);
        alumnoRepository.deleteById(id);
        return id;
    }
}
