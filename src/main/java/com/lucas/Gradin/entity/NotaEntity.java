package com.lucas.Gradin.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "nota")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class NotaEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_alumno")
    private AlumnoEntity alumno;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_evaluacion")
    private EvaluacionEntity evaluacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_asignatura")
    private AsignaturaEntity asignatura;

    private Long valor;

    //Constructores
    public NotaEntity() {
    }
    public NotaEntity(Long id, AlumnoEntity alumno, EvaluacionEntity evaluacion, AsignaturaEntity asignatura, Long valor) {
        this.id = id;
        this.alumno = alumno;
        this.evaluacion = evaluacion;
        this.asignatura = asignatura;
        this.valor = valor;
    }

    //Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AlumnoEntity getAlumno() {
        return alumno;
    }
    public void setAlumno(AlumnoEntity alumno) {
        this.alumno = alumno;
    }

    public EvaluacionEntity getEvaluacion() {
        return evaluacion;
    }
    public void setEvaluacion(EvaluacionEntity evaluacion) {
        this.evaluacion = evaluacion;
    }

    public AsignaturaEntity getAsignatura() {
        return asignatura;
    }
    public void setAsignatura(AsignaturaEntity asignatura) {
        this.asignatura = asignatura;
    }

    public Long getValor() {
        return valor;
    }
    public void setValor(Long valor) {
        this.valor = valor;
    }

}
