package com.example.aiboard.controller;

import com.example.aiboard.dto.TranslationResponse;
import com.example.aiboard.service.TranslateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class TranslateController {

    @Autowired
    private TranslateService translateService;

    @PostMapping(value = "/translate", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public TranslationResponse translateAudio(@RequestParam("audio") MultipartFile audio,
                                              @RequestParam("inputLang") String inputLang,
                                              @RequestParam("outputLang") String outputLang) {
        String translatedText = translateService.translateAudio(audio, inputLang, outputLang);
        return new TranslationResponse(translatedText);
    }
}
