package com.it.net.eureka.controller.user;

import com.it.net.eureka.dto.user.ChangeUserDto;
import com.it.net.eureka.dto.user.CreateUserDto;
import com.it.net.eureka.dto.user.LoginUserDto;
import com.it.net.eureka.model.user.User;
import com.it.net.eureka.service.user.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping(value = "/user", 
	produces = {MediaType.APPLICATION_JSON_VALUE}, 
	consumes = {MediaType.APPLICATION_JSON_VALUE})
public class AccessController {
	
	@Autowired
	private UserService userService;

	private User user;

	@PostMapping
	public ResponseEntity<User> signUp(@RequestBody @Validated CreateUserDto createUserDto) throws NoSuchAlgorithmException, InvalidKeySpecException {
		user = userService.createUser(createUserDto);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PostMapping(value = "/login")
	public ResponseEntity<User> logIn(@RequestBody @Validated LoginUserDto loginUserDto) throws NoSuchAlgorithmException, InvalidKeySpecException {
		user = userService.loginUser(loginUserDto);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PatchMapping(value = "/password")
	public ResponseEntity<User> changePassword(@RequestBody @Validated ChangeUserDto changeUserDto) throws NotFoundException, NoSuchAlgorithmException, InvalidKeySpecException {
		user = userService.changePassword(changeUserDto);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@PatchMapping(value = "/email")
	public ResponseEntity<User> changeEmail(@RequestBody @Validated ChangeUserDto changeUserDto) throws NotFoundException {
		user = userService.changeEmail(changeUserDto);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@PatchMapping(value = "/username")
	public ResponseEntity<User> changeUsername(@RequestBody @Validated ChangeUserDto changeUserDto) throws NotFoundException {
		user = userService.changeUsername(changeUserDto);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
}
