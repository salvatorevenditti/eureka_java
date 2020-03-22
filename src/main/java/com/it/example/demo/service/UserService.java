package com.it.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it.example.demo.dto.CreateUserDto;
import com.it.example.demo.dto.LoginUserDto;
import com.it.example.demo.model.User;
import com.it.example.demo.repo.UserRepository;
import com.it.example.demo.validator.CreateUserValidator;
import com.it.example.demo.validator.LoginUserValidator;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CreateUserValidator createUserValidator;
	
	@Autowired
	private LoginUserValidator loginUserValidator;
	
	public User createUser(CreateUserDto createUserDto, User user) {
		createUserValidator.validate(createUserDto);
		user.mapEntity(createUserDto);
		user = userRepo.save(user);
		return user;
	}
	
	public User loginUser(LoginUserDto loginUserDto, User user) {
		return loginUserValidator.validate(loginUserDto, user);
	}
	
}
