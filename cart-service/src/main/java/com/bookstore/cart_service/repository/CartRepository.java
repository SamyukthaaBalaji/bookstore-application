package com.bookstore.cart_service.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.cart_service.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{

    Optional<Cart> findByUserIdAndBookId(Long userId, Long bookId);

    List<Cart> findByUserId(Long userId);

}