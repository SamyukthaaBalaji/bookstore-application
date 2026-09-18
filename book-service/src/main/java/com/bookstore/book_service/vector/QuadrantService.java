package com.bookstore.book_service.vector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bookstore.book_service.entity.Book;

@Service
public class QuadrantService {
	@Autowired
	private EmbeddingService embeddingService;
	@Autowired
    private RestTemplate restTemplate;
	
	
	  private static final String QDRANT_URL =
	            "http://localhost:6333/collections/books/points";

	    public void saveBook(Book book) {

	        // Step 1
	        String text =
	                "Title: " + book.getTitle() +
	                "\nAuthor: " + book.getAuthor() +
	                "\nGenre: " + book.getGenre() +
	                "\nDescription: " + book.getDescription();

	        // Step 2
	        List<Double> embedding =
	                embeddingService.generateEmbedding(text);

	        // Step 3
	        // Build payload
	        
	        Map<String, Object> payload = new HashMap<>();

	        payload.put("title", book.getTitle());
	        payload.put("author", book.getAuthor());
	        payload.put("genre", book.getGenre());
	        payload.put("description", book.getDescription());
	        payload.put("price", book.getPrice());
	        payload.put("language", book.getLanguage());
	        
	        
	        
	        Map<String,Object> point = new HashMap<>();

	        point.put("id", book.getId());
	        point.put("vector", embedding);
	        point.put("payload", payload);

	        // Step 4
	        
	        Map<String,Object> request = new HashMap<>();

	        request.put("points", List.of(point));
	        
	        
	        
	        HttpHeaders headers = new HttpHeaders();

	        headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<Map<String,Object>> entity =
	                new HttpEntity<>(request, headers);	
	        
	     
	        // Send to Qdrant
	        try {

	            restTemplate.put(
	                    QDRANT_URL,
	                    entity
	            );

	            System.out.println("Book indexed successfully in Qdrant.");

	        } catch (Exception e) {

	            System.out.println("Failed to index book in Qdrant");
	            e.printStackTrace();

	        }

	    }



}
