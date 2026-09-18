package com.bookstore.book_service.service;

import java.util.List;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bookstore.book_service.entity.Book;
import com.bookstore.book_service.repository.BookRepository;
import com.bookstore.book_service.vector.QuadrantService;

@Service

public class BookService {
	@Autowired

    BookRepository bookRepository;
	
	@Autowired
	QuadrantService  qdrantService;

	public Page<Book> getAllBooks(Pageable pageable) {
	    return bookRepository.findAll(pageable);
	}

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public List<Book> getBooksByGenre(String genre) {
        return bookRepository.findByGenre(genre);
    }

    public List<Book> searchBooks(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    public Book createBook(Book book) {
//    	
         return bookRepository.save(book);
//    	qdrantService.saveBook(savedBook);
//
//    	return savedBook;
    }

    public Book updateBook(Long id, Book updatedBook) {
        updatedBook.setId(id);
        return bookRepository.save(updatedBook);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

	public List<String> getGenres() {
		
		return bookRepository.findAllGenres();
	}


public List<Book> searchByAuthor(String author) {
    return bookRepository.findByAuthorContainingIgnoreCase(author);
}
}
