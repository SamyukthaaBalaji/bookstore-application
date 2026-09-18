package com.bookstore.recommendation_service.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.recommendation_service.dto.BookDTO;
import com.bookstore.recommendation_service.service.RecommendationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.bookstore.recommendation_service.dto.ChatRequest;
import com.bookstore.recommendation_service.dto.OpenRouterChatRequest;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    // GET /recommendations/book/{bookId}
    // Returns full details of a book (Feign call to Book Service)
    @GetMapping("/book/{bookId}")
    public ResponseEntity<BookDTO> getBookDetails(@PathVariable Long bookId) {
        return ResponseEntity.ok(recommendationService.getBookDetails(bookId));
    }

    // GET /recommendations/similar/{bookId}
    // Returns books in the same genre, excluding this book
    @GetMapping("/similar/{bookId}")
    public ResponseEntity<List<BookDTO>> getSimilarBooks(@PathVariable Long bookId) {
        return ResponseEntity.ok(recommendationService.getSimilarBooks(bookId));
    }

    // GET /recommendations/genre/{genre}?excludeBookId=1
    // Returns all books in a genre
    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<BookDTO>> getByGenre(
            @PathVariable String genre,
            @RequestParam(required = false) Long excludeBookId) {
        return ResponseEntity.ok(
            recommendationService.getRecommendationsByGenre(genre, excludeBookId)
        );
    }
    
    @PostMapping("/chat")
    public ResponseEntity<String> chat(@RequestBody ChatRequest request){

        return ResponseEntity.ok(
                recommendationService.chatWithAI(request.getQuestion())
        );

    }
    
    
    
    @PostMapping("/chatai")
    public ResponseEntity<String> chat(@RequestBody OpenRouterChatRequest request) {

        String response = recommendationService.chatwithai(request.getQuestion());

        return ResponseEntity.ok(response);
    }
}