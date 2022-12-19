package com.lucas.Gradin.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "profesor")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProfesorEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dni;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String pass;

    private boolean superuser;



    @OneToMany(mappedBy = "profesor", fetch = FetchType.LAZY)
    private List<AsignaturaEntity> asignaturas;

    //Constructores
    public ProfesorEntity() {
        this.asignaturas = new ArrayList<AsignaturaEntity>();
    }
    public ProfesorEntity(String nombre, String apellido1, String apellido2, String email, String dni, boolean isSuperuser) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.email = email;
        this.superuser = isSuperuser;
        this.asignaturas = new ArrayList<AsignaturaEntity>();
    }


    //Getters y Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getDni() {
        return dni;
    }
    public void setDni(String dNI) {
        dni = dNI;
    }

    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getAsignaturas() {
        return asignaturas.size();
    }

    public boolean isSuperuser() {
        return this.superuser;
    }

    @PreRemove
    public void nullify() {
        for (AsignaturaEntity oAsignatura : asignaturas) {
            oAsignatura.setProfesor(null); // Lo mismo que forEach
        }
    }
}
