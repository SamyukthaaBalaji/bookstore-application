package com.bookstore.review_service.reviewservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.review_service.entity.Review;
import com.bookstore.review_service.repository.ReviewRepository;

@Service
public class ReviewService {

	@Autowired
    private ReviewRepository reviewRepository;
	

    // Add a new review
	public Review addReview(Review review) {
        return reviewRepository.save(review);
    }
	
	public List<Review> getReviewsByBookId(Long bookId) {
	    return reviewRepository.findByBookId(bookId);
	}
    // Get all reviews by a specific user
	public List<Review> getReviewsByUserId(Long UserId) {
		return reviewRepository.findByUserId(UserId);
	}
	
	 // Get average rating for a book
    public Double getAverageRating(Long bookId) {
        return reviewRepository.findAverageRatingByBookId(bookId);
    }
    

    // Get review count for a book
    public Long getReviewCount(Long bookId) {
        return reviewRepository.countByBookId(bookId);
    }
    
    public Review UpdateReview(Long id, Review updatedReview) {
    	Review existing =reviewRepository.findById(id).orElseThrow(()->new RuntimeException("Review not found with id: " + id));
    	existing.setRating(updatedReview.getRating());
    	existing.setComment(updatedReview.getComment());
    	
    	return reviewRepository.save(existing);
    	
    }
    
 // Delete a review
    public void deleteReview(Long id) {
        if (!reviewRepository.existsById(id)) {
            throw new RuntimeException("Review not found with id: " + id);
        }
        reviewRepository.deleteById(id);
    }
	
	
	
	

}
