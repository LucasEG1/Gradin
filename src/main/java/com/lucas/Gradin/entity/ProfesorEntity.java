package com.lucas.Gradin.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "profesor")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProfesorEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String email;
    private String DNI;

    @OneToMany(mappedBy = "profesor", fetch = FetchType.LAZY)
    private List<AsignaturaEntity> asignaturas;

    //Constructores
    public ProfesorEntity() {
        this.asignaturas = new ArrayList<AsignaturaEntity>();
    }
    public ProfesorEntity(Long id, String nombre, String apellido, String email, String dNI) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        DNI = dNI;
        this.asignaturas = new ArrayList<AsignaturaEntity>();
    }

    //Getters y Setters
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getDNI() {
        return DNI;
    }
    public void setDNI(String dNI) {
        DNI = dNI;
    }

    public int getAsignaturas() {
        return asignaturas.size();
    }
}
