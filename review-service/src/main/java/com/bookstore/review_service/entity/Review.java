package com.bookstore.review_service.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "reviews")
public class Review {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getBookId() {
		return bookId;
	}
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	private Long bookId;
    public Review(Long bookId, Long userId, String username, int rating, String comment, LocalDateTime createdAt,
			Long id) {
		super();
		this.bookId = bookId;
		this.userId = userId;
		this.username = username;
		this.rating = rating;
		this.comment = comment;
		this.createdAt = createdAt;
		this.id = id;
	}
	@Override
	public String toString() {
		return "Review [bookId=" + bookId + ", userId=" + userId + ", username=" + username + ", rating=" + rating
				+ ", comment=" + comment + ", createdAt=" + createdAt + ", id=" + id + "]";
	}
	private Long userId;
    private String username;
    private int rating;
    private String comment;
    private LocalDateTime createdAt;
    private Long id;
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
	
    public Review() {}
	
	

}
