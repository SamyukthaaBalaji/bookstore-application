package com.bookstore.book_service.vector;

public class QdrantPayload {
	private String title;
    private String author;
    private String genre;
    private String description;
    private Double price;

    public QdrantPayload() {
    }

    public QdrantPayload(String title, String author, String genre,
                         String description, Double price) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.description = description;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
