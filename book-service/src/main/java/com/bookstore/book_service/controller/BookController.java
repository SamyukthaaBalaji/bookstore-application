package com.bookstore.book_service.controller;

import java.util.List;


import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.bookstore.book_service.entity.Book;
import com.bookstore.book_service.service.BookService;



@RestController
@RequestMapping("/api/books")


public class BookController {

	@Autowired
     BookService bookService;
	
	@GetMapping
	public ResponseEntity<Page<Book>> getAllBooks(Pageable pageable) {
	    return ResponseEntity.ok(bookService.getAllBooks(pageable));
	}
	
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<Book>> getBooksByGenre(@PathVariable String genre) {
        return ResponseEntity.ok(bookService.getBooksByGenre(genre));
    }
    
    @GetMapping("/allgenres")
    public ResponseEntity<List<String>> getGenres() {
        return ResponseEntity.ok(bookService.getGenres());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam String title) {
        return ResponseEntity.ok(bookService.searchBooks(title));
    }

    @PostMapping
    public ResponseEntity<Book> createBook( @RequestBody Book book) {
        return ResponseEntity.status(HttpStatus.SC_CREATED).body(bookService.createBook(book));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        return ResponseEntity.ok(bookService.updateBook(id, book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/author")
    public ResponseEntity<List<Book>> searchByAuthor(@RequestParam String author) {

        return ResponseEntity.ok(bookService.searchByAuthor(author));

    }
    
}