package com.example.Nutricion.controller;

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

import com.example.Nutricion.model.Alimentacion;
import com.example.Nutricion.service.AlimentacionService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/alimentacion")
public class AlimentacionController {


    @Autowired
    private AlimentacionService alimentacionService;


    @PostMapping
    public ResponseEntity<?> agregarAlimento(@RequestBody Alimentacion alimento) {
        return alimentacionService.agregarAlimento(alimento);
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> listarPorEmail(@PathVariable String email) {
        return alimentacionService.listarPorEmail(email);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<?> finalizarDia(@PathVariable String email) {
        return alimentacionService.finalizarDia(email);
    }

    @PutMapping("/item/{id}")
    public ResponseEntity<?> actualizarAlimento(@PathVariable Integer id, @RequestBody Alimentacion alimento) {
        return alimentacionService.actualizarAlimento(id, alimento);
    }

    @DeleteMapping("/item/{id}")
    public ResponseEntity<?> eliminarAlimento(@PathVariable Integer id) {
        return alimentacionService.eliminarAlimento(id);
    }

}
