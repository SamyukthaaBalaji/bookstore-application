package com.bookstore.cart_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bookstore.cart_service.dto.Bookdto;
import com.bookstore.cart_service.dto.CartResponse;
import com.bookstore.cart_service.entity.Cart;
import com.bookstore.cart_service.feign.BookClient;
import com.bookstore.cart_service.repository.CartRepository;

@Service
public class CartService {
	 private final CartRepository repository;
	    private final BookClient bookClient;

	    public CartService(CartRepository repository,
	                       BookClient bookClient) {

	        this.repository = repository;
	        this.bookClient = bookClient;

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
	                    bookClient.getBook(cart.getBookId());

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
