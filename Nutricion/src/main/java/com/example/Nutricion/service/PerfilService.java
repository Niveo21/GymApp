package com.example.Nutricion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Nutricion.model.Perfil;
import com.example.Nutricion.repository.PerfilRepository;

@Service
public class PerfilService {


    @Autowired
    private PerfilRepository perfilRepository;


    public ResponseEntity<?> agregarPerfil(Perfil perfil){

        if (perfil.getEdad() <= 0) {
            return ResponseEntity.badRequest().body("La edad debe ser mayor a 0");
        }

        if (perfil.getPeso() <= 0) {
            return ResponseEntity.badRequest().body("El peso debe ser mayor a 0");
        }

        if (perfil.getAltura() <= 0) {
            return ResponseEntity.badRequest().body("La altura debe ser mayor a 0");
        }

        perfilRepository.save(perfil);
        return ResponseEntity.ok().body("Perfil agregado exitosamente");
    }

    public ResponseEntity<?> obtenerPerfil(String email) {
        return perfilRepository.findById(email)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    




}
