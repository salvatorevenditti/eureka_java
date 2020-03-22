package com.it.net.eureka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it.net.eureka.dto.CreateUserDto;
import com.it.net.eureka.dto.LoginUserDto;
import com.it.net.eureka.model.User;
import com.it.net.eureka.repo.UserRepository;
import com.it.net.eureka.validator.CreateUserValidator;
import com.it.net.eureka.validator.LoginUserValidator;

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
