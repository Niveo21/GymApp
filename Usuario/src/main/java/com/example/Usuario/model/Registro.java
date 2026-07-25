package com.example.Usuario.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Registro {

    @Id
    private String email;
    private String name;
    private String password;

    @OneToMany(mappedBy = "registro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SesionEntrenamiento> sesiones = new ArrayList<>();

    public Registro() {
        this.name = "";
        this.email = "";
        this.password = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<SesionEntrenamiento> getSesiones() {
        return sesiones;
    }

    public void setSesiones(List<SesionEntrenamiento> sesiones) {
        this.sesiones = sesiones;
    }

    

}
