package com.example.Nutricion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Nutricion.model.Alimentacion;

public interface AlimentacionRepository extends JpaRepository<Alimentacion, Integer> {

    List<Alimentacion> findByEmail(String email);

    void deleteByEmail(String email);

}
