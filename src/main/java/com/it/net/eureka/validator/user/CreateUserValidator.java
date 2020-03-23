package com.it.net.eureka.validator.user;

import javax.validation.ValidationException;

import org.springframework.stereotype.Component;

import com.it.net.eureka.dto.user.CreateUserDto;

@Component
public class CreateUserValidator extends UserValidator {
	
	public void validate(CreateUserDto createUserDto) throws ValidationException {
		validateInputFields(createUserDto);
		super.checkIfAlreadyExists(createUserDto.getUsername(), createUserDto.getEmail());
	}
	
	public void validateInputFields(CreateUserDto createUserDto) {
		if(!super.checkEmail(createUserDto.getEmail()) || !super.checkPsw(createUserDto.getPassword())) {
			throw new ValidationException("Check your email and password!\n"
					+ "ExamplePassword --> Example.00\n"
					+ "ExampleEmail --> examplemail@gmail.com");
		} else if(!createUserDto.getPassword().equals(createUserDto.getConfirmPassword())) {
			throw new ValidationException("Password and ConfirmPassword fields do not match!");
		}
	}
}
