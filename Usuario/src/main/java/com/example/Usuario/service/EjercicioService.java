package com.example.Usuario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.Usuario.model.Ejercicio;
import com.example.Usuario.repository.EjercicioRepository;


@Service
public class EjercicioService {


    @Autowired
    private EjercicioRepository ejercicioRepository;



    public String agregarEjercicio(@RequestBody Ejercicio ejercicio) {

        if (ejercicio.getEjercicio() == null || ejercicio.getEjercicio().isEmpty()) {
            return "El nombre del ejercicio es obligatorio";
        }

        if (ejercicio.getRepeticiones() <= 0) {
            return "Las repeticiones deben ser mayores a 0";
        }

        if (ejercicio.getSeries() <= 0) {
            return "Las series deben ser mayores a 0";
        }

        ejercicioRepository.save(ejercicio);
        return "Ejercicio agregado exitosamente";
    }

}
