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
	 public Cart addToCart(Cart cart) {

		    // Get book details from Book Service
		    Bookdto book = bookServiceClient.getBook(cart.getBookId());

		    if (book == null) {
		        throw new RuntimeException("Book Service is unavailable");
		    }

		    // Check available stock
		    if (book.getStock() == null || book.getStock() <= 0) {
		        throw new RuntimeException("Book is out of stock");
		    }

		    // Find existing cart item
		    Optional<Cart> existing = repository.findByUserIdAndBookId(
		            cart.getUserId(), cart.getBookId());

		    int currentQuantity = existing
		            .map(Cart::getQuantity)
		            .orElse(0);

		    // Prevent adding more copies than available
		    if (currentQuantity >= book.getStock()) {
		        throw new RuntimeException("Not enough stock available");
		    }

		    // Decrease stock by one
		    bookServiceClient.updateStock(
		            cart.getBookId(), book.getStock() - 1);

		    // Update cart quantity
		    if (existing.isPresent()) {
		        Cart existingCart = existing.get();
		        existingCart.setQuantity(existingCart.getQuantity() + 1);
		        return repository.save(existingCart);
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

	    public void remove(Long id) {

	        // Find the cart item before deleting it
	        Cart cart = repository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Cart item not found"));

	        // Get current book stock
	        Bookdto book = bookServiceClient.getBook(cart.getBookId());

	        if (book == null) {
	            throw new RuntimeException("Book Service is unavailable");
	        }

	        // Restore all copies reserved by this cart item
	        bookServiceClient.updateStock(
	                cart.getBookId(),
	                book.getStock() + cart.getQuantity());

	        // Delete the cart item
	        repository.delete(cart);
	    }

}
