package com.example.Nutricion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Nutricion.model.Perfil;
import com.example.Nutricion.service.PerfilService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/perfil")
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    @PostMapping
    public ResponseEntity<?> agregarPerfil(@RequestBody Perfil perfil) {
        return perfilService.agregarPerfil(perfil);
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> obtenerPerfil(@PathVariable String email) {
        return perfilService.obtenerPerfil(email);
    }

}
