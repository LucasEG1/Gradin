package com.lucas.Gradin.entity;

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

@Entity
@Table(name = "evaluacion")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EvaluacionEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToMany(mappedBy = "evaluacion", fetch = FetchType.LAZY)
    private List<NotaEntity> notas;
    
    private int numero;
    
    //Constructor
    public EvaluacionEntity() {
        this.notas = null;
    }
    public EvaluacionEntity(Long id, List<NotaEntity> notas, int numero) {
        this.id = id;
        this.notas = notas;
        this.numero = numero;
    }
    //Getters y Setters
    public Long getId() {
        return id;
    }
    public int getNotas() {
        return notas.size();
    }

    public int getNumero() {
        return numero;
    }

    @PreRemove
    private void nullify() {
        for (NotaEntity nota : notas) {
            nota.setEvaluacion(null);
        }
    }
}
