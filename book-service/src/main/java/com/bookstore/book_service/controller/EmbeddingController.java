package com.bookstore.book_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.book_service.vector.EmbeddingService;
import com.bookstore.book_service.vector.QuadrantService;

@RestController
public class EmbeddingController {
	 @Autowired
	    private EmbeddingService embeddingService;

	 @Autowired
	 private QuadrantService qdrantService;
	    @GetMapping("/test-embedding")
	    public List<Double> testEmbedding() {

	        String text =
	                """
	                Title: Spring Boot in Action
	                Author: Craig Walls
	                Genre: Programming
	                Description: Beginner friendly Spring Boot book.
	                """;

	        return embeddingService.generateEmbedding(text);
	    }
	    @GetMapping("/test-embedding-size")
	    public int testEmbeddingSize() {

	        List<Double> embedding =
	                embeddingService.generateEmbedding("Spring Boot beginner guide");

	        return embedding.size();
	    }
	    
	    
}
