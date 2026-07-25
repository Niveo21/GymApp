package com.example.Usuario.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Usuario.service.ChatbotService;

@RestController
@RequestMapping("/chatbot")
@CrossOrigin(origins = "*")
public class ChatbotController {

    @Autowired
    private ChatbotService chatbotService;

    @PostMapping
    public Map<String, String> responder(@RequestBody Map<String, String> body) {
    String respuesta = chatbotService.responder(body.get("mensaje"));
    return Map.of("respuesta", respuesta);
}
    

}
