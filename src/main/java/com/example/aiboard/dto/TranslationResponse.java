package com.example.aiboard.dto;

public class TranslationResponse {
    private String translatedText;

    public TranslationResponse(String translatedText) {
        this.translatedText = translatedText;
    }

    public String getTranslatedText() {
        return translatedText;
    }
}
