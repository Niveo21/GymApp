package com.example.Usuario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Usuario.model.Registro;
import com.example.Usuario.model.SesionEntrenamiento;
import com.example.Usuario.repository.RegistroRepository;
import com.example.Usuario.repository.SesionEntrenamientoRepository;

@Service
public class SesionEntrenamientoService {


    @Autowired
    private SesionEntrenamientoRepository sesionEntrenamientoRepository;

    @Autowired
    private RegistroRepository registroRepository;



    public ResponseEntity<?> agregarSesion(String email, SesionEntrenamiento sesion) {
    if (sesion.getNombreSesion() == null || sesion.getNombreSesion().isEmpty()) {
        return ResponseEntity.badRequest().body("El nombre de la sesión es obligatorio");
    }
    if (sesion.getDuracion() <= 0) {
        return ResponseEntity.badRequest().body("La duración de la sesión debe ser mayor a 0");
    }
    if (sesion.getEjercicios() == null || sesion.getEjercicios().isEmpty()) {
        return ResponseEntity.badRequest().body("La sesión debe contener al menos un ejercicio");
    }

    Registro registro = registroRepository.findById(email).orElse(null);
    if (registro == null) {
        return ResponseEntity.badRequest().body("Usuario no encontrado");
    }

    sesion.setRegistro(registro);
    sesion.getEjercicios().forEach(ejercicio -> ejercicio.setSesion(sesion));

    SesionEntrenamiento guardada = sesionEntrenamientoRepository.save(sesion);
    return ResponseEntity.ok(guardada);
}


    public List<SesionEntrenamiento> listarSesiones(String email) {
    return sesionEntrenamientoRepository.findByRegistroEmail(email);
    }



    //te odio
    public ResponseEntity<?> actualizarSesion(int id, SesionEntrenamiento datos) {
        SesionEntrenamiento existente = sesionEntrenamientoRepository.findById(id).orElse(null);
        if (existente == null) {
            return ResponseEntity.badRequest().body("Sesión no encontrada");
        }
        if (datos.getNombreSesion() == null || datos.getNombreSesion().isEmpty()) {
            return ResponseEntity.badRequest().body("El nombre de la sesión es obligatorio");
        }
        if (datos.getDuracion() <= 0) {
            return ResponseEntity.badRequest().body("La duración debe ser mayor a 0");
        }
        if (datos.getEjercicios() == null || datos.getEjercicios().isEmpty()) {
            return ResponseEntity.badRequest().body("La sesión debe contener al menos un ejercicio");
        }

        existente.setNombreSesion(datos.getNombreSesion());
        existente.setDuracion(datos.getDuracion());
        existente.setKcal(datos.getKcal());

        existente.getEjercicios().clear();
        datos.getEjercicios().forEach(e -> {
            e.setSesion(existente);
            existente.getEjercicios().add(e);
        });

        return ResponseEntity.ok(sesionEntrenamientoRepository.save(existente));
    }

    public String eliminarSesion(int id) {
        if (!sesionEntrenamientoRepository.existsById(id)) {
            return "Sesión de entrenamiento no encontrada";
        }

        sesionEntrenamientoRepository.deleteById(id);
        return "Sesión de entrenamiento eliminada exitosamente";
    }

}
