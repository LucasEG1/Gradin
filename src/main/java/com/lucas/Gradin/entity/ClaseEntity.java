package com.lucas.Gradin.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "clase")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ClaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_aula")
    private AulaEntity aula;

    private String nombre;

    @OneToMany(mappedBy = "clase", fetch = FetchType.LAZY)
    private List<AlumnoEntity> alumnos;

    //Constructores
    public ClaseEntity() {
        this.alumnos = new ArrayList<AlumnoEntity>();
    }
    public ClaseEntity(String id, AulaEntity aula, String nombre, List<AlumnoEntity> alumnos) {
        this.id = id;
        this.aula = aula;
        this.nombre = nombre;
        this.alumnos = new ArrayList<AlumnoEntity>();
    }

    //Getters y Setters
    public String getId() {
        return id;
    }
    public AulaEntity getAula() {
        return aula;
    }
    public void setAula(AulaEntity aula) {
        this.aula = aula;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getAlumnos() {
        return alumnos.size();
    }

    @PreRemove
    private void nullify() {
        for (AlumnoEntity alumno : alumnos) {
            alumno.setClase(null);
        }
    }
}
