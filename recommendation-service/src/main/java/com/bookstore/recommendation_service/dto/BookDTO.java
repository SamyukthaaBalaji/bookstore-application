package com.bookstore.recommendation_service.dto;

import java.time.LocalDate;

public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private String genre;       // matches your Book entity
    private String isbn;
    private String description;
    private Double price;
    private LocalDate publishedDate;
    private String language;

    public BookDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public LocalDate getPublishedDate() { return publishedDate; }
    public void setPublishedDate(LocalDate publishedDate) { this.publishedDate = publishedDate; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }
}