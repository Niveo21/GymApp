package com.example.Usuario.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ChatbotService {

    @Value("${openrouter.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public ChatbotService(RestTemplateBuilder builder) {
    this.restTemplate = builder.build();
}

@SuppressWarnings("unchecked")
public String responder(String mensaje) {
    String url = "https://openrouter.ai/api/v1/chat/completions";

    Map<String, Object> body = Map.of(
        "model", "openai/gpt-oss-20b:free",
        "max_tokens", 200,
        "messages", List.of(
            Map.of("role", "system", "content", "Eres un asistente de una app de gimnasio. Responde siempre en texto plano, sin markdown (sin **, sin tablas, sin listas con guiones). Sé breve y directo, máximo 2-3 oraciones."),
            Map.of("role", "user", "content", mensaje)
        )
    );

    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "Bearer " + apiKey);
    headers.set("Content-Type", "application/json");

    HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

    Map<String, Object> response = restTemplate.postForObject(url, request, Map.class);

    List<Map<String, Object>> choices = (List<Map<String, Object>>) response.get("choices");
    Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");

    return (String) message.get("content");
}

}
