package com.example.user_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.user_service.dto.LoginRequest;
import com.example.user_service.entity.User;
import com.example.user_service.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	 @Autowired
	    private UserService service;
	 
	 
	 
	 @PostMapping("/signup")
	    public ResponseEntity<User> signup(@RequestBody User user) {

	        return ResponseEntity.ok(service.signup(user));
	    }

	    @PostMapping("/login")
	    public ResponseEntity<User> login(
	            @RequestBody LoginRequest request) {

	        return ResponseEntity.ok(service.login(request));
	    }

	    @GetMapping
	    public ResponseEntity<List<User>> getAllUsers() {

	        return ResponseEntity.ok(service.getAllUsers());
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<User> getUser(
	            @PathVariable Long id) {

	        return ResponseEntity.ok(service.getUser(id));
	    }
}
