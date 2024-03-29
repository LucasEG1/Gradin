package com.lucas.Gradin.service;

import com.lucas.Gradin.entity.ProfesorEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.lucas.Gradin.entity.NotaEntity;
import com.lucas.Gradin.exception.ResourceNotFoundException;
import com.lucas.Gradin.repository.NotaRepository;

import javax.servlet.http.HttpSession;

@Service
public class NotaService {

    @Autowired
    private NotaRepository oNotaRepository;

    @Autowired
    private AsignaturaService oAsignaturaService;

    @Autowired
    private AuthService oAuthService;

    // VALIDACIONES
    public void validate(Long id) {
        if (!oNotaRepository.existsById(id)) {
            throw new ResourceNotFoundException("No existe nota con id " + id + ".");
        }
    }

    public NotaEntity getOne(Long id) {
        oAuthService.OnlyOwnerOrSuperuser(id);
        validate(id);
        return oNotaRepository.findById(id).get();
    }

    public Page<NotaEntity> getPage(Pageable oPageable, Long idAlumno, Long idAsignatura, Long idEvaluacion, Long idProfesor) {
        oAuthService.OnlyOwnerOrSuperuser(idProfesor);

        if (idAlumno == null && idEvaluacion == null && idAsignatura == null) {
            return oNotaRepository.findAll(oPageable);
        } else if (idAlumno == null && idEvaluacion == null) {
            return oNotaRepository.findByAsignaturaId(idAsignatura, oPageable);
        } else if (idAlumno == null && idAsignatura == null) {
            return oNotaRepository.findByEvaluacionId(idEvaluacion, oPageable);
        } else if (idEvaluacion == null && idAsignatura == null) {
            return oNotaRepository.findByAlumnoId(idAlumno, oPageable);
        } else if (idAlumno == null) {
            return oNotaRepository.findByEvaluacionIdAndAsignaturaId(idEvaluacion, idAsignatura, oPageable);
        } else if (idEvaluacion == null) {
            return oNotaRepository.findByAlumnoIdAndAsignaturaId(idAlumno, idAsignatura, oPageable);
        } else if (idAsignatura == null) {
            return oNotaRepository.findByAlumnoIdAndEvaluacionId(idAlumno, idEvaluacion, oPageable);
        } else {
            return oNotaRepository.findByAlumnoIdAndEvaluacionIdAndAsignaturaId(idAlumno, idEvaluacion, idAsignatura, oPageable);
        }
    }

    public Long create(NotaEntity nuevaNotaEntity) {
        oAuthService.OnlySuperuser();
        NotaEntity existeNota = oNotaRepository.findByAlumnoIdAndEvaluacionIdAndAsignaturaId(
            nuevaNotaEntity.getAlumno().getId(),
            nuevaNotaEntity.getEvaluacion().getId(),
            nuevaNotaEntity.getAsignatura().getId());
        if (existeNota != null) {
            throw new ResourceNotFoundException("Ya existe una nota con los datos proporcionados.");
        } else {
            nuevaNotaEntity.setId(0L);
            return oNotaRepository.save(nuevaNotaEntity).getId();
        }
    }

    public NotaEntity update(NotaEntity oNotaEntity, Long idProfesor) {
        oAuthService.OnlyOwnerOrSuperuser(idProfesor);
        validate(oNotaEntity.getId());
        return oNotaRepository.save(oNotaEntity);
    }

    public Long delete(Long id) {
        oAuthService.OnlySuperuser();
        validate(id);
        oNotaRepository.deleteById(id);
        return id;
    }

    public Long count() {
        oAuthService.OnlySuperuser();
        return oNotaRepository.count();
    }

}
