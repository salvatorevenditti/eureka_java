package com.it.net.eureka.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.it.net.eureka.dto.user.ChangeUserDto;
import com.it.net.eureka.dto.user.CreateUserDto;
import com.it.net.eureka.dto.user.LoginUserDto;
import com.it.net.eureka.model.user.User;
import com.it.net.eureka.service.user.UserService;

import javassist.NotFoundException;

@RestController
@RequestMapping(value = "/user", 
	produces = {MediaType.APPLICATION_JSON_VALUE}, 
	consumes = {MediaType.APPLICATION_JSON_VALUE})
public class AccessController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<User> signUp (@RequestBody @Validated CreateUserDto createUserDto, User user) {
		user = userService.createUser(createUserDto, user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@GetMapping(value = "/login")
	public ResponseEntity<User> logIn(@RequestBody @Validated LoginUserDto loginUserDto, User user) {
		user = userService.loginUser(loginUserDto, user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PatchMapping(value = "/password")
	public ResponseEntity<User> changePassword(@RequestBody @Validated ChangeUserDto changeUserDto, User user) throws NotFoundException {
		user = userService.changePassword(changeUserDto, user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PatchMapping(value = "/email")
	public ResponseEntity<User> changeEmail(@RequestBody @Validated ChangeUserDto changeUserDto, User user) throws NotFoundException {
		user = userService.changeEmail(changeUserDto, user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PatchMapping(value = "/username")
	public ResponseEntity<User> changeUsername(@RequestBody @Validated ChangeUserDto changeUserDto, User user) throws NotFoundException {
		user = userService.changeUsername(changeUserDto, user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
}
