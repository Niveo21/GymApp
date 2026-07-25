package com.example.Usuario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Usuario.model.Registro;
import com.example.Usuario.repository.RegistroRepository;

@Service
public class RegistroService {


    @Autowired
    private RegistroRepository registroRepository;


    public String RegistrarUsuario(Registro registro) {
        if (registro.getEmail() == null || registro.getEmail().isEmpty()) {
            return "El email es obligatorio";
        }

        if (registroRepository.existsById(registro.getEmail())) {
            return "El email ya está registrado";
        }

        if (registro.getName() == null || registro.getName().isEmpty()) {
            return "El nombre es obligatorio";
        }

        if (registro.getPassword() == null || registro.getPassword().isEmpty() || registro.getPassword().length() < 6) {
            return "La contraseña es obligatoria y debe tener al menos 6 caracteres";
        }
        registroRepository.save(registro);
        return "Registro Exitoso";
    }


    public boolean existeUsuario(String email) {
        return registroRepository.existsById(email);
    }

    public String obtenerNombre(String email) {
        return registroRepository.findById(email)
                .map(Registro::getName)
                .orElse("");
    }

    public String login(String email, String password) {
        if (email == null || email.isEmpty()) {
            return "El email es obligatorio";
        }

        if (password == null || password.isEmpty()) {
            return "La contraseña es obligatoria";
        }

        Registro registro = registroRepository.findById(email).orElse(null);
        if (registro == null) {
            return "Usuario no encontrado";
        }

        if (!registro.getPassword().equals(password)) {
            return "Contraseña incorrecta";
        }

        return "Login Exitoso";
    }

}
