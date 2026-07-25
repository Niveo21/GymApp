package com.example.Usuario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Usuario.model.Ejercicio;
import com.example.Usuario.service.EjercicioService;

@RestController
@RequestMapping("/ejercicios")
public class EjercicioController {


    @Autowired
    private EjercicioService ejercicioService;



    @PostMapping
    public String agregarEjercicio(Ejercicio ejercicio) {
        return ejercicioService.agregarEjercicio(ejercicio);
    }

}
