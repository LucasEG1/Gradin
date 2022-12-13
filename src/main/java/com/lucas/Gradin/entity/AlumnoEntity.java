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
@Table(name = "alumno")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AlumnoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_clase")
    private ClaseEntity clase;
    
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String DNI;
    private String NIA;
    private String email;
    private String telefono;


    @OneToMany(mappedBy = "alumno", fetch = FetchType.LAZY)
    private List<NotaEntity> notas;

    //Constructores
    public AlumnoEntity() {
        this.notas = new ArrayList<NotaEntity>();
    }
    public AlumnoEntity(Long id, ClaseEntity clase, String nombre, String apellido1, String apellido2, String dNI, String nIA, String email, String telefono) {
        this.id = id;
        this.clase = clase;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        DNI = dNI;
        NIA = nIA;
        this.email = email;
        this.telefono = telefono;
        this.notas = new ArrayList<NotaEntity>();
    }

    //Getters y Setters
    public Long getId() {
        return id;
    }
    
    public ClaseEntity getClase() {
        return clase;
    }
    public void setClase(ClaseEntity clase) {
        this.clase = clase;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getDNI() {
        return DNI;
    }
    public void setDNI(String dNI) {
        DNI = dNI;
    }

    public String getNIA() {
        return NIA;
    }
    public void setNIA(String nIA) {
        NIA = nIA;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getNotas() {
        return notas.size();
    }

}
