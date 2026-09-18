package com.bookstore.recommendation_service.Ai;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class AiService {
	 @Autowired
	    private RestTemplate restTemplate;
	 public String askAI(String prompt) {

		 OllamaRequest request =
			        new OllamaRequest(
			                "llama3.2",
			                prompt,
			                false
			        );

		 // Ollama API URL
	        String url = "http://localhost:11434/api/generate";

	        // Send POST request to Ollama
	        OllamaResponse response = restTemplate.postForObject(
	                url,
	                request,
	                OllamaResponse.class
	        );

	        // Return only the AI generated text
	        if (response != null) {
	            return response.getResponse();
	        }

	        return "No response received from AI.";
	    }

}
