package com.example.user_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.user_service.dto.LoginRequest;
import com.example.user_service.entity.User;
import com.example.user_service.repository.UserRepository;

@Service
public class UserService {

	 @Autowired
	    private UserRepository repository;
	 
	 public User signup(User user) {
		 if(repository.findByEmail(user.getEmail()).isPresent()) {
			    throw new RuntimeException("Email already exists");
		 }
		 user.setRole("User");
		 return repository.save(user);
	 }
	 public User login(LoginRequest request) {

		    User user = repository.findByEmailAndPassword(
		            request.getEmail(),
		            request.getPassword()
		    ).orElseThrow(() ->
		            new RuntimeException("Invalid Email or Password")
		    );

		    if (!user.getPassword().equals(request.getPassword())) {
		        throw new RuntimeException("Invalid Email or Password");
		    }

		    return user;
		}
	  public List<User> getAllUsers() {
		  return repository.findAll();
	  }
	  public User getUser(Long id) {
		  return repository.findById(id)  .orElseThrow(() ->
          new RuntimeException("User not found"));
	  }
}
