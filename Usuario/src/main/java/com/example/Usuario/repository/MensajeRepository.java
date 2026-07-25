package com.example.Usuario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Usuario.model.Mensaje;

public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {

    @Query("SELECT m FROM Mensaje m WHERE " +
           "(m.remitente = :usuario1 AND m.destinatario = :usuario2) OR " +
           "(m.remitente = :usuario2 AND m.destinatario = :usuario1) " +
           "ORDER BY m.fechaEnvio ASC")
    List<Mensaje> findConversacion(@Param("usuario1") String usuario1, @Param("usuario2") String usuario2);
}
