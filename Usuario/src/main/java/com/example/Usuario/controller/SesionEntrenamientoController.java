package com.example.Usuario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Usuario.model.SesionEntrenamiento;
import com.example.Usuario.service.SesionEntrenamientoService;


@RequestMapping("/sesiones")
@RestController
@CrossOrigin(origins = "*")
public class SesionEntrenamientoController {



    @Autowired
    private SesionEntrenamientoService sesionEntrenamientoService;



    @PostMapping("/{email}")
    public ResponseEntity<?> agregarSesion(@PathVariable String email, @RequestBody SesionEntrenamiento sesion) {
        return sesionEntrenamientoService.agregarSesion(email, sesion);
    }

    @GetMapping("/{email}")
    public List<SesionEntrenamiento> listarSesiones(@PathVariable String email) {
    return sesionEntrenamientoService.listarSesiones(email);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarSesion(@PathVariable int id, @RequestBody SesionEntrenamiento sesion) {
        return sesionEntrenamientoService.actualizarSesion(id, sesion);
    }

    @DeleteMapping("/{id}")
    public String eliminarSesion(@PathVariable int id) {
        return sesionEntrenamientoService.eliminarSesion(id);
    }

}
