package com.bookstore.review_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.review_service.entity.Review;
import com.bookstore.review_service.reviewservice.ReviewService;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
	@Autowired
	 private ReviewService reviewService;
	
	

    // POST /reviews — Add a review
	
	@PostMapping
	public ResponseEntity<Review> addReview(@RequestBody Review review){
		Review saved = reviewService.addReview(review);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
		
	}

	// GET /reviews/book/{bookId} — Get all reviews for a book
	
	@GetMapping("/book/{bookId}")
	public ResponseEntity<List<Review>> getReviewsByBook(@PathVariable Long bookId){
		return ResponseEntity.ok(reviewService.getReviewsByBookId(bookId));
	}
	
	// GET /reviews/user/{userId} — Get all reviews by a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Review>> getReviewsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(reviewService.getReviewsByUserId(userId));
    }

    // GET /reviews/book/{bookId}/average — Average rating for a book
    @GetMapping("/book/{bookId}/average")
    public ResponseEntity<Double> getAverageRating(@PathVariable Long bookId) {
        return ResponseEntity.ok(reviewService.getAverageRating(bookId));
    }
    
 // GET /reviews/book/{bookId}/count — Review count for a book
    @GetMapping("/book/{bookId}/count")
    public ResponseEntity<Long> getReviewCount(@PathVariable Long bookId) {
        return ResponseEntity.ok(reviewService.getReviewCount(bookId));
    }

    // PUT /reviews/{id} — Update a review
    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody Review review) {
        return ResponseEntity.ok(reviewService.UpdateReview(id, review));
    }

    // DELETE /reviews/{id} — Delete a review
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.ok("Review deleted successfully");
    }
}
