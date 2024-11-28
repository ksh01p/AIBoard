package com.example.aiboard.controller;

import com.example.aiboard.domain.TranslationResponse;
import com.example.aiboard.service.TranslateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class TranslateController {

    @Autowired
    private TranslateService translateService;

    @PostMapping(value = "/transcribe", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public TranslationResponse transcribeAudio(@RequestParam("audio") MultipartFile audio) {
        String recognizedText = translateService.recognizeSpeech(audio);
        return new TranslationResponse(recognizedText);
    }
}
