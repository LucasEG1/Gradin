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

    @JoinColumn(name = "isbn_libro")
    private String isbnLibro;

    @OneToMany(mappedBy = "asignatura", fetch = FetchType.LAZY)
    private List<NotaEntity> notas;

    //Constructores
    public AsignaturaEntity() {
        this.notas = new ArrayList<NotaEntity>();
    }
    public AsignaturaEntity(Long id, ProfesorEntity profesor, String nombre, String isbnLibro) {
        this.id = id;
        this.profesor = profesor;
        this.nombre = nombre;
        this.isbnLibro = isbnLibro;
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

    public String getIsbnLibro() {
        return isbnLibro;
    }
    public void setIsbnLibro(String isbnLibro) {
        this.isbnLibro = isbnLibro;
    }

    public int getNotas() {
        return notas.size();
    }

    @PreRemove
    public void nullify() {
        for (NotaEntity nota : notas) {
            nota.setAsignatura(null);
        }
    }

}
