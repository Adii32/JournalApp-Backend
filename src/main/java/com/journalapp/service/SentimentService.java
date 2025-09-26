package com.journalapp.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;


@Service
public class SentimentService {

    private  WebClient webClient;
    @Value("${hfApiToken}")
    private  String hfApiToken;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public SentimentService(WebClient.Builder builder, @Value("${hfApiToken}") String hfApiToken) {
        System.out.println("Hugging Face API Token: " + hfApiToken); // Debug print
        this.hfApiToken = hfApiToken;
        this.webClient = builder
        	    .baseUrl("https://api-inference.huggingface.co/models/distilbert/distilbert-base-uncased-finetuned-sst-2-english")
        	    .defaultHeader("Authorization", "Bearer " + hfApiToken)
        	    .build();


    }
    

    public Mono<SentimentResponse> analyzeSentiment(String text) {
        Map<String, String> input = Map.of("inputs", text);
        return webClient.post()
            .bodyValue(input)
            .retrieve()
            .bodyToMono(String.class)
            .map(this::parseSentiment);
    }

    private SentimentResponse parseSentiment(String jsonResponse) {
        try {
            JsonNode root = objectMapper.readTree(jsonResponse);

            // Handle nested array structure
            if (root.isArray() && root.size() > 0 && root.get(0).isArray()) {
                JsonNode firstResult = root.get(0).get(0); // take first candidate
                String label = firstResult.get("label").asText();
                double score = firstResult.get("score").asDouble();
                return new SentimentResponse(label, score);
            } 
            else if (root.has("error")) {
                System.out.println("Hugging Face API Error: " + root.get("error").asText());
                return new SentimentResponse("error", 0.0);
            } else {
                return new SentimentResponse("error", 0.0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new SentimentResponse("error", 0.0);
        }
    }



    // DTO class for response
    public static class SentimentResponse {
        private String label;
        private double score;

        public SentimentResponse(String label, double score) {
            this.label = label;
            this.score = score;
        }

        public String getLabel() {
            return label;
        }

        public double getScore() {
            return score;
        }
    }
}
