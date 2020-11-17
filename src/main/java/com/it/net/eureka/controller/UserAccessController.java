package com.it.net.eureka.controller;

import com.it.net.eureka.dto.ChangeUserDto;
import com.it.net.eureka.dto.CreateUserDto;
import com.it.net.eureka.dto.LoginUserDto;
import com.it.net.eureka.model.Email;
import com.it.net.eureka.model.EmailType;
import com.it.net.eureka.model.Master;
import com.it.net.eureka.model.User;
import com.it.net.eureka.service.EmailService;
import com.it.net.eureka.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserAccessController {

	@Autowired
	private UserService userService;

	@Autowired
	private EmailService emailService;

	@PostMapping
	public ResponseEntity<User> signUp(@RequestBody @Validated CreateUserDto createUserDto) {
		User user = userService.createUser(createUserDto);
		emailService.mapAndSendEmail(new Email(), EmailType.CREATE, createUserDto);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PostMapping(value = "/login")
	public ResponseEntity<User> logIn(@RequestBody @Validated LoginUserDto loginUserDto) {
		User user = userService.loginUser(loginUserDto);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PatchMapping(value = "/password")
	public ResponseEntity<User> changePassword(@RequestBody @Validated ChangeUserDto changeUserDto) throws NotFoundException {
		User user = userService.changePassword(changeUserDto);
		emailService.mapAndSendEmail(new Email(), EmailType.PASSWORD, changeUserDto);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@PatchMapping(value = "/email")
	public ResponseEntity<User> changeEmail(@RequestBody @Validated ChangeUserDto changeUserDto) throws NotFoundException {
		User user = userService.changeEmail(changeUserDto);
		emailService.mapAndSendEmail(new Email(), EmailType.EMAIL, changeUserDto);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@PatchMapping(value = "/username")
	public ResponseEntity<User> changeUsername(@RequestBody @Validated ChangeUserDto changeUserDto) throws NotFoundException {
		User user = userService.changeUsername(changeUserDto);
		emailService.mapAndSendEmail(new Email(), EmailType.USERNAME, changeUserDto);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping(value = "/{correlationId}")
	public ResponseEntity<User> getUser(@RequestParam String correlationId) throws NotFoundException {
		User user = userService.getUser(correlationId);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
}
