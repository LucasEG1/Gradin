package com.lucas.Gradin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lucas.Gradin.entity.NotaEntity;

public interface NotaRepository extends JpaRepository<NotaEntity, Long> {

    Page<NotaEntity> findByAlumnoId(Long idAlumno, Pageable oPageable);
    Page<NotaEntity> findByEvaluacionId(Long idEvaluacion, Pageable oPageable);
    Page<NotaEntity> findByAsignaturaId(Long idAsignatura, Pageable oPageable);
    Page<NotaEntity> findByAlumnoIdAndEvaluacionId(Long idAlumno, Long idEvaluacion, Pageable oPageable);
    Page<NotaEntity> findByAlumnoIdAndAsignaturaId(Long idAlumno, Long idAsignatura, Pageable oPageable);
    Page<NotaEntity> findByEvaluacionIdAndAsignaturaId(Long idEvaluacion, Long idAsignatura, Pageable oPageable);
    Page<NotaEntity> findByAlumnoIdAndEvaluacionIdAndAsignaturaId(Long idAlumno, Long idEvaluacion, Long idProfesor, Pageable oPageable);
    NotaEntity findByAlumnoIdAndEvaluacionIdAndAsignaturaId(Long idAlumno, Long idEvaluacion, Long idProfesor);
}
