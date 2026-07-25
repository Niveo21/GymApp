package com.example.Usuario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Usuario.model.Mensaje;
import com.example.Usuario.repository.MensajeRepository;
import com.example.Usuario.repository.RegistroRepository;

@Service
public class MensajeService {

    @Autowired
    private MensajeRepository mensajeRepository;

    @Autowired
    private RegistroRepository registroRepository;

    public ResponseEntity<?> enviarMensaje(Mensaje mensaje) {
        if (mensaje.getContenido() == null || mensaje.getContenido().isEmpty()) {
            return ResponseEntity.badRequest().body("El mensaje no puede estar vacío");
        }
        if (mensaje.getRemitente() == null || mensaje.getRemitente().isEmpty()) {
            return ResponseEntity.badRequest().body("El remitente es obligatorio");
        }
        if (mensaje.getDestinatario() == null || mensaje.getDestinatario().isEmpty()) {
            return ResponseEntity.badRequest().body("El destinatario es obligatorio");
        }
        if (mensaje.getRemitente().equals(mensaje.getDestinatario())) {
            return ResponseEntity.badRequest().body("No puedes enviarte mensajes a ti mismo");
        }
        if (!registroRepository.existsById(mensaje.getDestinatario())) {
            return ResponseEntity.badRequest().body("El usuario destinatario no existe");
        }

        return ResponseEntity.ok(mensajeRepository.save(mensaje));
    }

    public List<Mensaje> obtenerConversacion(String usuario1, String usuario2) {
        return mensajeRepository.findConversacion(usuario1, usuario2);
    }
}
