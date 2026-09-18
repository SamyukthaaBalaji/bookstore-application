	package com.bookstore.cart_service.controller;
	
	import java.util.List;
	
	import org.springframework.web.bind.annotation.CrossOrigin;
	import org.springframework.web.bind.annotation.DeleteMapping;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RestController;
	
	import com.bookstore.cart_service.dto.CartResponse;
	import com.bookstore.cart_service.entity.Cart;
	import com.bookstore.cart_service.service.CartService;
	
	@RestController
	@RequestMapping("/api/cart")
	
	public class CartController {
		  private final CartService service;
	
		    public CartController(CartService service) {
	
		        this.service = service;
	
		    }
	
		    @PostMapping
		    public Cart addToCart(
		            @RequestBody Cart cart){
	
		        return service.addToCart(cart);
	
		    }
	
		    @GetMapping("/{userId}")
		    public List<CartResponse> getCart(
		            @PathVariable Long userId){
	
		        return service.getCart(userId);
	
		    }
	
		    @DeleteMapping("/{id}")
		    public void remove(
		            @PathVariable Long id){
	
		        service.remove(id);
	
		    }
	
	}
