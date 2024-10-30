package com.example.aiboard.domain;

import lombok.Getter;

@Getter
public class TranslationResponse {
    private String translatedText;

    public TranslationResponse(String translatedText) {
        this.translatedText = translatedText;
    }

}
