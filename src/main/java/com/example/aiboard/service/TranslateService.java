package com.example.aiboard.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;

@Service
public class TranslateService {

    private static final Logger logger = LoggerFactory.getLogger(TranslateService.class);

    @Value("${openai.api.key}")
    private String openAiApiKey;

    private static final String WHISPER_API_URL = "https://api.openai.com/v1/audio/transcriptions";

    public String recognizeSpeech(MultipartFile audio) {

        // 오디오 파일 확인
        if (audio == null || audio.isEmpty()) {
            logger.warn("로그: 오디오 없음.");
            return "음성 인식 실패: 오디오 파일이 없습니다.";
        } else {
            logger.info("로그: 오디오 있음.");
        }

        try {
            logger.info("Starting speech recognition.");
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

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(WHISPER_API_URL, requestEntity, String.class);

            // API 응답 처리
            if (response.getStatusCode() == HttpStatus.OK) {
                logger.info("Response from Whisper API received.");
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(response.getBody());
                return root.path("text").asText();
            } else {
                logger.error("Failed to recognize speech: Status code {}", response.getStatusCode());
                return "음성 인식 실패: ";
            }
        } catch (HttpClientErrorException e) {
            logger.error("Error during speech recognition: {} - {}", e.getStatusCode(), e.getStatusText());
            return "음성 인식 실패: 인증 오류입니다."; // getStatusText() 사용
        } catch (Exception e) {
            logger.error("Error during speech recognition: {}", e.getMessage(), e);
            return "음성 인식 실패";
        }
    }
}
