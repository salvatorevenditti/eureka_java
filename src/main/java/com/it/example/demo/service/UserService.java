package com.it.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it.example.demo.dto.CreateUserDto;
import com.it.example.demo.model.User;
import com.it.example.demo.repo.UserRepository;
import com.it.example.demo.validator.CreateUserValidator;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	CreateUserValidator createUserValidator;
	
	public User createUser(CreateUserDto createUserDto, User user) {
		
		createUserValidator.validate(createUserDto);
		user.mapEntity(createUserDto);
		user = userRepo.save(user);
		
		return user;
	}
	
}
