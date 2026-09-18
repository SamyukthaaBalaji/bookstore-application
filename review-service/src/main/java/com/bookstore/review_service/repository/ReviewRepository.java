package com.bookstore.review_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookstore.review_service.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository <Review,Long>{
	List<Review> findBookById(Long bookId);
	List<Review> findByUserId(Long userId);
	@Query("SELECT AVG(r.rating) FROM Review r WHERE r.bookId = :bookId")
    Double findAverageRatingByBookId(Long bookId);

    Long countByBookId(Long bookId);
	

}
