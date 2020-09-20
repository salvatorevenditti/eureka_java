package com.it.net.eureka.controller.user;

import com.it.net.eureka.dto.user.ChangeUserDto;
import com.it.net.eureka.dto.user.CreateUserDto;
import com.it.net.eureka.dto.user.LoginUserDto;
import com.it.net.eureka.model.email.Email;
import com.it.net.eureka.model.email.EmailType;
import com.it.net.eureka.model.user.User;
import com.it.net.eureka.service.email.EmailService;
import com.it.net.eureka.service.user.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user",
		produces = {MediaType.APPLICATION_JSON_VALUE},
		consumes = {MediaType.APPLICATION_JSON_VALUE})
public class AccessController {

	@Autowired
	private UserService userService;

	@Autowired
	private EmailService emailService;

	@PostMapping
	public ResponseEntity<User> signUp(@RequestBody @Validated CreateUserDto createUserDto) {
		User user = userService.createUser(createUserDto);
		emailService.mapAndSendEmail(new Email(), EmailType.CREATE_USER, createUserDto);
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
}
