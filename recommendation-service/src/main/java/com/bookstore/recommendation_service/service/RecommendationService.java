package com.bookstore.recommendation_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.recommendation_service.Ai.AiService;
import com.bookstore.recommendation_service.dto.BookDTO;
import com.bookstore.recommendation_service.feign.BookClient;
import com.bookstore.recommendation_service.openrouterai.AIService;

@Service
public class RecommendationService {
	@Autowired
	private AIService aiservice;
	@Autowired
	private AiService aiService;
	
	@Autowired
    private BookClient bookClient;
	
	public BookDTO getBookDetails(Long bookId) {
		return bookClient.getBookById(bookId);
		
	}
	
	// Given a bookId, find its genre then recommend similar books
	
	public List<BookDTO> getRecommendationsByGenre(String genre, Long excludeBookId){
		List<BookDTO> books=bookClient.getBooksByGenre(genre);
		return books.stream()
                .filter(book -> !book.getId().equals(excludeBookId))
                .collect(Collectors.toList());
	}
	
	// Given a bookId, find its genre then recommend similar books
    public List<BookDTO> getSimilarBooks(Long bookId) {
        BookDTO book = bookClient.getBookById(bookId);   // Feign call to Book Service
        String genre = book.getGenre();
        return getRecommendationsByGenre(genre, bookId); // exclude the same book
    }
    public String chatWithAI(String question) {

        // Fetch all books using Feign
        List<BookDTO> books = bookClient.getAllBooks();

        // Build the prompt
        StringBuilder prompt = new StringBuilder();

        prompt.append("You are an AI Librarian.\n\n");
        prompt.append("Recommend ONLY books from the list below.\n\n");

        prompt.append("Available Books:\n\n");

        for (BookDTO book : books) {

            prompt.append("Title: ")
                    .append(book.getTitle())
                    .append("\n");

            prompt.append("Author: ")
                    .append(book.getAuthor())
                    .append("\n");

            prompt.append("Genre: ")
                    .append(book.getGenre())
                    .append("\n");

            prompt.append("Price: ₹")
                    .append(book.getPrice())
                    .append("\n");

            prompt.append("Description: ")
                    .append(book.getDescription())
                    .append("\n");

            prompt.append("---------------------------------------\n");
        }

        prompt.append("\nUser Question:\n");
        prompt.append(question);

        prompt.append("\n\n");
        prompt.append("Recommend only books from the Available Books list.");
        prompt.append(" Do not recommend books that are not in the list.");
        prompt.append(" Explain why you recommended each book.");

        // Send prompt to Ollama
        String answer = aiService.askAI(prompt.toString());

        return answer;
    }
    
    
    
    public String chatwithai(String question) {

        // Step 1: Get all books from Book Service using Feign
        List<BookDTO> books = bookClient.getAllBooks();

        // Step 2: Build prompt
        StringBuilder prompt = new StringBuilder();

        prompt.append("You are an AI Librarian.\n");
        prompt.append("Answer the user's question using ONLY the books given below.\n");
        prompt.append("If no suitable books exist, say 'No matching books found.'\n\n");

        prompt.append("User Question:\n");
        prompt.append(question);
        prompt.append("\n\n");

        prompt.append("Available Books:\n\n");

        for (BookDTO book : books) {

            prompt.append("Title: ")
                  .append(book.getTitle())
                  .append("\n");

            prompt.append("Author: ")
                  .append(book.getAuthor())
                  .append("\n");

            prompt.append("Genre: ")
                  .append(book.getGenre())
                  .append("\n");

            prompt.append("Price: ")
                  .append(book.getPrice())
                  .append("\n");

            prompt.append("Description: ")
                  .append(book.getDescription())
                  .append("\n");

            prompt.append("Language: ")
                  .append(book.getLanguage())
                  .append("\n");

            prompt.append("----------------------------------\n");
        }

        // Step 3: Send prompt to AI
        return aiService.askAI(prompt.toString());
    }

}
