package com.bookstore.book_service.vector;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmbeddingService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String OLLAMA_URL =
            "http://localhost:11434/api/embed";

    public List<Double> generateEmbedding(String text) {

        EmbeddingRequest request =
                new EmbeddingRequest(
                        "nomic-embed-text",
                        text
                );

        EmbeddingResponse response =
                restTemplate.postForObject(
                        OLLAMA_URL,
                        request,
                        EmbeddingResponse.class
                );

        return response.getEmbeddings().get(0);
    }
}