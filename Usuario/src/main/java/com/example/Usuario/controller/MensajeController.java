package com.example.Usuario.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Usuario.model.Mensaje;
import com.example.Usuario.service.MensajeService;

@RestController
@RequestMapping("/mensajes")
public class MensajeController {

    @Autowired
    private MensajeService mensajeService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @PostMapping
    public ResponseEntity<?> enviarMensaje(@RequestBody Mensaje mensaje) {
        return mensajeService.enviarMensaje(mensaje);
    }

    @GetMapping("/{usuario1}/{usuario2}")
    public List<Mensaje> obtenerConversacion(@PathVariable String usuario1, @PathVariable String usuario2) {
        return mensajeService.obtenerConversacion(usuario1, usuario2);
    }

    @MessageMapping("/chat.enviar")
    public void enviarMensajeWs(@Payload Mensaje mensaje) {
        ResponseEntity<?> resultado = mensajeService.enviarMensaje(mensaje);

        if (resultado.getStatusCode().is2xxSuccessful()) {
            Mensaje guardado = (Mensaje) resultado.getBody();
            messagingTemplate.convertAndSend("/topic/mensajes." + mensaje.getDestinatario(), guardado);
            messagingTemplate.convertAndSend("/topic/mensajes." + mensaje.getRemitente(), guardado);
        } else {
            messagingTemplate.convertAndSend("/topic/errores." + mensaje.getRemitente(), resultado.getBody());
        }
    }
}
