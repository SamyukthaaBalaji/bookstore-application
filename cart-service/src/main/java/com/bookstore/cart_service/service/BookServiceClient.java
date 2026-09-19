package com.bookstore.cart_service.service;
import org.springframework.stereotype.Service;

import com.bookstore.cart_service.dto.Bookdto;
import com.bookstore.cart_service.feign.BookClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class BookServiceClient {

    private final BookClient bookClient;

    public BookServiceClient(BookClient bookClient) {
        this.bookClient = bookClient;
    }

    @CircuitBreaker(
            name = "bookService",
            fallbackMethod = "bookServiceFallback"
    )
    public Bookdto getBook(Long bookId) {

        return bookClient.getBook(bookId);
    }

    public Bookdto bookServiceFallback(Long bookId, Exception ex) {

        System.out.println(
                "Book Service is unavailable. Fallback triggered for book: "
                + bookId
        );

        return null;
    }
}