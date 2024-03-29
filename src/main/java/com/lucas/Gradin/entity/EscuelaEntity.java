package com.lucas.Gradin.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "escuela")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EscuelaEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String direccion;
    private String CP;

    @OneToMany(mappedBy = "escuela", fetch = FetchType.LAZY)
    private List<AulaEntity> aulas;

    //Constructores
    public EscuelaEntity() {
        this.aulas = new ArrayList<AulaEntity>();
    }
    public EscuelaEntity(Long id, String nombre, String direccion, String CP, List<AulaEntity> aulas) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.CP = CP;
        this.aulas = new ArrayList<AulaEntity>();
    }

    //Getters y Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCP() {
        return CP;
    }

    public void setCP(String cP) {
        CP = cP;
    }

    public int getAulas() {
        return aulas.size();
    }

    @PreRemove
    public void nullify() {
        for (AulaEntity aula : aulas) {
            aula.setEscuela(null);
        }
    }
}
