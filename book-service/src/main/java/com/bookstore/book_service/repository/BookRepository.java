package com.bookstore.book_service.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookstore.book_service.entity.Book;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByGenre(String genre);

    List<Book> findByAuthor(String author);

    List<Book> findByTitleContainingIgnoreCase(String title);
    
    @Query("SELECT DISTINCT b.genre FROM Book b")
    List<String> findAllGenres();
    
    
    List<Book> findByAuthorContainingIgnoreCase(String author);
    
    @Modifying
    @Query("""
        UPDATE Book b
        SET b.stock = b.stock - 1
        WHERE b.id = :id AND b.stock > 0
        """)
    int decreaseStock(@Param("id") Long id);
}
