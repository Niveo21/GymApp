package com.example.Usuario.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Ejercicio {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ejercicio;
    private Integer series;
    private Integer repeticiones;
    private Integer kg;
    private Integer RIR;

    @ManyToOne
    @JoinColumn(name = "sesion_id")
    @JsonIgnore
    private SesionEntrenamiento sesion;

    public Ejercicio() {
        this.ejercicio = "";
        this.series = null;
        this.repeticiones = null;
        this.kg = null;
        this.RIR = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(String ejercicio) {
        this.ejercicio = ejercicio;
    }

    public Integer getSeries() {
        return series;
    }

    public void setSeries(Integer series) {
        this.series = series;
    }

    public Integer getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(Integer repeticiones) {
        this.repeticiones = repeticiones;
    }

    public Integer getKg() {
        return kg;
    }

    public void setKg(Integer kg) {
        this.kg = kg;
    }

    public Integer getRIR() {
        return RIR;
    }

    public void setRIR(Integer RIR) {
        this.RIR = RIR;
    }

    public SesionEntrenamiento getSesion() {
        return sesion;
    }

    public void setSesion(SesionEntrenamiento sesion) {
        this.sesion = sesion;
    }

    
    

}
