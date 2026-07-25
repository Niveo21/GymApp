package com.example.Nutricion.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Perfil {


    @Id
    private String email;
    private int edad;
    private int peso;
    private int altura;
    private String sexo;
    private String objetivo;

    public Perfil() {
        this.email = "";
        this.edad = 0;
        this.peso = 0;
        this.altura = 0;
        this.sexo = "";
        this.objetivo = "";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }


    


}
