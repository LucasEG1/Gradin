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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Table(name = "aula")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AulaEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_escuela")
    private EscuelaEntity escuela;

    private String nombre;

    @OneToMany(mappedBy = "aula", fetch = FetchType.LAZY)
    private List<ClaseEntity> clases;

    //Constructores
    public AulaEntity() {
        this.clases = new ArrayList<ClaseEntity>();
    }
    public AulaEntity(Long id, EscuelaEntity escuela, String nombre, List<ClaseEntity> clases) {
        this.id = id;
        this.escuela = escuela;
        this.nombre = nombre;
        this.clases = new ArrayList<ClaseEntity>();
    }

    //Getters y Setters
    public Long getId() {
        return id;
    }
    public EscuelaEntity getEscuela() {
        return escuela;
    }
    public void setEscuela(EscuelaEntity escuela) {
        this.escuela = escuela;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getClases() {
        return clases.size();
    }
}
