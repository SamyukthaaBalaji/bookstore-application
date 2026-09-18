package com.bookstore.book_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookstore.book_service.entity.Book;

@Repository

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByGenre(String genre);

    List<Book> findByAuthor(String author);

    List<Book> findByTitleContainingIgnoreCase(String title);
    
    @Query("SELECT DISTINCT b.genre FROM Book b")
    List<String> findAllGenres();
    
    
    List<Book> findByAuthorContainingIgnoreCase(String author);
}
