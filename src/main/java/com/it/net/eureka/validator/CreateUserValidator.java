package com.it.net.eureka.validator;

import javax.validation.ValidationException;

import org.springframework.stereotype.Component;

import com.it.net.eureka.dto.CreateUserDto;

@Component
public class CreateUserValidator extends UserValidator {
	
	public void validate(CreateUserDto createUserDto) throws ValidationException {
		validateInputFields(createUserDto.getEmail(), createUserDto.getPassword());
		checkIfAlreadyExists(createUserDto.getUsername(), createUserDto.getEmail());
	}
	
	public void validateInputFields(String email, String pwd) {
		
		if(!checkEmail(email) || !checkPsw(pwd)) {
			throw new ValidationException("Check your email and password!\n"
					+ "ExamplePassword --> Example.00\n"
					+ "ExampleEmail --> examplemail@gmail.com");
		}
	}
}
