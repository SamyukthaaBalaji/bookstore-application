package com.bookstore.cart_service.dto;

public class CartResponse {
	 private Long cartId;

	    private Long userId;

	    private int quantity;

	    private Bookdto book;

	    public CartResponse() {
	    }

	    public Long getCartId() {
	        return cartId;
	    }

	    public void setCartId(Long cartId) {
	        this.cartId = cartId;
	    }

	    public Long getUserId() {
	        return userId;
	    }

	    public void setUserId(Long userId) {
	        this.userId = userId;
	    }

	    public int getQuantity() {
	        return quantity;
	    }

	    public void setQuantity(int quantity) {
	        this.quantity = quantity;
	    }

	    public Bookdto getBook() {
	        return book;
	    }

	    public void setBook(Bookdto book) {
	        this.book = book;
	    }

}
