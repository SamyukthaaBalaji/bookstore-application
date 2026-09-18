package com.bookstore.recommendation_service.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bookstore.recommendation_service.dto.BookDTO;

@FeignClient(name= "book-service")
public interface BookClient {
	
	
	// matches GET /api/books/{id} in your BookController
	@GetMapping("/api/books/{id}")
	 BookDTO getBookById(@PathVariable("id") Long id);
	
	 // matches GET /api/books/genre/{genre} in your BookController
    @GetMapping("/api/books/genre/{genre}")
    List<BookDTO> getBooksByGenre(@PathVariable("genre") String genre);
    
    @GetMapping("/api/books")
    List<BookDTO> getAllBooks();
	

}
