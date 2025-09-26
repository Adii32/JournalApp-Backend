package com.journalapp.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.journalapp.service.SentimentService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/sentiment")
public class SentimentController {

    private final SentimentService sentimentService;

    @Autowired
    public SentimentController(SentimentService sentimentService) {
        this.sentimentService = sentimentService;
    }

    @PostMapping
    public Mono<ResponseEntity<SentimentService.SentimentResponse>> analyzeSentiment(@RequestBody TextRequest textRequest) {
        return sentimentService.analyzeSentiment(textRequest.getText())
                .map(ResponseEntity::ok);
    }

    public static class TextRequest {
        private String text;

        public TextRequest() {
        }

        public TextRequest(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
