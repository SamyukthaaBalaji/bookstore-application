package com.bookstore.recommendation_service.openrouterai;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bookstore.recommendation_service.openrouterai.request.Message;
import com.bookstore.recommendation_service.openrouterai.request.OpenRouterRequest;
import com.bookstore.recommendation_service.openrouterai.response.OpenRouterresponse;

@Service
public class AIService {
	  private final RestTemplate restTemplate;

	    public AIService(RestTemplate restTemplate) {
	        this.restTemplate = restTemplate;
	    }

	    @Value("${openrouter.api.url}")
	    private String apiUrl;

	    @Value("${openrouter.api.key}")
	    private String apiKey;

	    @Value("${openrouter.model}")
	    private String model;

	    public String askAI(String prompt) {

	        // Create user message
	        Message message = new Message("user", prompt);

	        // Create request body
	        OpenRouterRequest request =
	                new OpenRouterRequest(model, List.of(message));

	        // Create headers
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        headers.setBearerAuth(apiKey);

	        // Optional but recommended by OpenRouter
	        headers.set("HTTP-Referer", "http://localhost");
	        headers.set("X-Title", "Book Recommendation System");

	        // Combine headers + body
	        HttpEntity<OpenRouterRequest> entity =
	                new HttpEntity<>(request, headers);

	        // Call OpenRouter
	        ResponseEntity<OpenRouterresponse> response =
	                restTemplate.exchange(
	                        apiUrl,
	                        HttpMethod.POST,
	                        entity,
	                        OpenRouterresponse.class
	                );

	        // Check response
	        if (response.getBody() != null
	                && response.getBody().getChoices() != null
	                && !response.getBody().getChoices().isEmpty()) {

	            return response.getBody()
	                    .getChoices()
	                    .get(0)
	                    .getMessage()
	                    .getContent();
	        }

	        return "No response from AI.";
	    }

}
