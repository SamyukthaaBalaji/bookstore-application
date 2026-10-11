package com.bookstore.cart_service.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookstore.cart_service.dto.Bookdto;

@FeignClient(name="book-service")

public interface BookClient {
	 @GetMapping("/api/books/{id}")
	    Bookdto getBook(@PathVariable Long id);
	 
	  @PutMapping("/api/books/{id}/stock")
	    Bookdto updateStock(
	            @PathVariable("id") Long id,
	            @RequestParam("quantity") Integer quantity);
	}


