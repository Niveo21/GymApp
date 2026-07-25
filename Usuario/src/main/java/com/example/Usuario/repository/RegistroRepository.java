package com.example.Usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Usuario.model.Registro;

public interface RegistroRepository extends JpaRepository<Registro, String> {

}
