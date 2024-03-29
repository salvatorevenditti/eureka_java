package com.it.net.eureka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.it.net.eureka.dto.CreateUserDto;
import com.it.net.eureka.dto.LoginUserDto;
import com.it.net.eureka.model.User;
import com.it.net.eureka.service.UserService;

@Controller
public class AccessController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping(value = "/signup", 
			consumes = "application/json", 
			produces = "application/json")
	@ResponseBody
	public ResponseEntity<User> signUp (@RequestBody @Validated CreateUserDto createUserDto, User user) {
		
		user = userService.createUser(createUserDto, user);
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@GetMapping(value = "/login",
			consumes = "application/json", 
			produces = "application/json")
	@ResponseBody
	public ResponseEntity<User> logIn(@RequestBody @Validated LoginUserDto loginUserDto, User user) {
		
		user = userService.loginUser(loginUserDto, user);
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
}
