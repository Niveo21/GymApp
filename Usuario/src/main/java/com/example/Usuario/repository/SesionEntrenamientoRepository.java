package com.example.Usuario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Usuario.model.SesionEntrenamiento;

public interface SesionEntrenamientoRepository extends JpaRepository<SesionEntrenamiento, Integer> {

    List<SesionEntrenamiento> findByRegistroEmail(String email);

}
