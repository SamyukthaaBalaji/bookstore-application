package com.bookstore.cart_service.service;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bookstore.cart_service.dto.Bookdto;
import com.bookstore.cart_service.dto.CartResponse;
import com.bookstore.cart_service.entity.Cart;
import com.bookstore.cart_service.service.BookServiceClient;
import com.bookstore.cart_service.repository.CartRepository;

@Service
public class CartService {
	 private final CartRepository repository;
	 private final BookServiceClient bookServiceClient;

	 public CartService(CartRepository repository,
             BookServiceClient bookServiceClient) {

this.repository = repository;
this.bookServiceClient = bookServiceClient;
}
	    public Cart addToCart(Cart cart){

	        Optional<Cart> existing =
	                repository.findByUserIdAndBookId(
	                        cart.getUserId(),
	                        cart.getBookId());

	        if(existing.isPresent()){

	            Cart c = existing.get();

	            c.setQuantity(c.getQuantity()+1);

	            return repository.save(c);

	        }

	        cart.setQuantity(1);

	        return repository.save(cart);

	    }

	    public List<CartResponse> getCart(Long userId){

	        List<Cart> cartItems =
	                repository.findByUserId(userId);

	        List<CartResponse> response =
	                new ArrayList<>();

	        for(Cart cart : cartItems){

	        	Bookdto book =
	        	        bookServiceClient.getBook(cart.getBookId());

	            CartResponse dto =
	                    new CartResponse();

	            dto.setCartId(cart.getId());
	            dto.setUserId(cart.getUserId());
	            dto.setQuantity(cart.getQuantity());
	            dto.setBook(book);

	            response.add(dto);

	        }

	        return response;

	    }

	    public void remove(Long id){

	        repository.deleteById(id);

	    }


}
