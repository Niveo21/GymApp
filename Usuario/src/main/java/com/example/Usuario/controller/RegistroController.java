package com.example.Usuario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Usuario.model.Registro;
import com.example.Usuario.service.RegistroService;

@RequestMapping("/registro")
@RestController
@CrossOrigin(origins = "*")
public class RegistroController {


    @Autowired
    private RegistroService registroService;


    @PostMapping
    public String RegistrarUsuario(@RequestBody Registro registro) {
        return registroService.RegistrarUsuario(registro);
    }

    @PostMapping("/login")
    public String login(@RequestBody Registro registro) {
        return registroService.login(registro.getEmail(), registro.getPassword());
    }

    @GetMapping("/existe/{email}")
    public boolean existeUsuario(@PathVariable String email) {
        return registroService.existeUsuario(email);
    }

    @GetMapping("/nombre/{email}")
    public String obtenerNombre(@PathVariable String email) {
        return registroService.obtenerNombre(email);
    }

}
