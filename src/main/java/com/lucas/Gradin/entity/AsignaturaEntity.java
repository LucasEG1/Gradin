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
@Table(name = "asignatura")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AsignaturaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_profesor")
    private ProfesorEntity profesor;
    
    private String nombre;
    private String ISBNLibro;

    @OneToMany(mappedBy = "asignatura", fetch = FetchType.LAZY)
    private List<NotaEntity> notas;

    //Constructores
    public AsignaturaEntity() {
        this.notas = new ArrayList<NotaEntity>();
    }
    public AsignaturaEntity(Long id, ProfesorEntity profesor, String nombre, String iSBNLibro) {
        this.id = id;
        this.profesor = profesor;
        this.nombre = nombre;
        ISBNLibro = iSBNLibro;
        this.notas = new ArrayList<NotaEntity>();
    }

    //Getters y Setters
    public Long getId() {
        return id;
    }

    public ProfesorEntity getProfesor() {
        return profesor;
    }
    public void setProfesor(ProfesorEntity profesor) {
        this.profesor = profesor;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getISBNLibro() {
        return ISBNLibro;
    }
    public void setISBNLibro(String iSBNLibro) {
        ISBNLibro = iSBNLibro;
    }

    public int getNotas() {
        return notas.size();
    }
}
