package com.example.aiboard.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TranslateService {

    @Value("sk-RKZ9yN_z2BeDa9DiDJBuLbiwNnHS8nD8y0mQgx26MfT3BlbkFJVJ1Q6Jbq4TgU4TED-D_dpNRALJ2G6cUgyTT8nFZkcA")
    private String openAiApiKey;

    private static final String WHISPER_API_URL = "https://api.openai.com/v1/audio/transcriptions";
    private static final String CHATGPT_API_URL = "https://api.openai.com/v1/chat/completions";

    public String translateAudio(MultipartFile audio, String inputLang, String outputLang) {
        try {
            // 1. Whisper API로 음성 인식
            String recognizedText = recognizeSpeech(audio, inputLang);

            // 2. ChatGPT API로 번역 수행
            return translateText(recognizedText, outputLang);

        } catch (Exception e) {
            e.printStackTrace();
            return "번역 실패";
        }
    }

    private String recognizeSpeech(MultipartFile audio, String inputLang) throws IOException {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(openAiApiKey);
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new ByteArrayResource(audio.getBytes()) {
            @Override
            public String getFilename() {
                return "audio.wav";
            }
        });
        body.add("model", "whisper-1");
        body.add("language", inputLang);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(WHISPER_API_URL, requestEntity, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        return root.path("text").asText();
    }

    private String translateText(String recognizedText, String outputLang) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(openAiApiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-3.5-turbo");
        requestBody.put("messages", List.of(
                Map.of("role", "system", "content", "Translate the following text to " + outputLang),
                Map.of("role", "user", "content", recognizedText)
        ));

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(CHATGPT_API_URL, requestEntity, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        return root.path("choices").get(0).path("message").path("content").asText();
    }
}
