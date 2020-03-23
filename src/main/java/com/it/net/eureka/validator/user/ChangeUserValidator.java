package com.it.net.eureka.validator.user;

import javax.validation.ValidationException;

import org.springframework.stereotype.Component;

import com.it.net.eureka.dto.user.ChangeUserDto;

@Component
public class ChangeUserValidator extends UserValidator {

	public void validateNewUsername(ChangeUserDto changeUserDto) {
		if(!super.checkEmail(changeUserDto.getEmail())) 
			throw new ValidationException("Email's pattern is not correct!");
		if(checkIfAlreadyExists(changeUserDto.getUsername()) != null)
			throw new ValidationException("Username Already Exists!");
	}

	public void validateNewPassword(ChangeUserDto changeUserDto) {
		if(!super.checkPsw(changeUserDto.getPassword())) 
			throw new ValidationException("Password's pattern is not correct!");
	}

	public void validateNewEmail(ChangeUserDto changeUserDto) {
		if(checkIfAlreadyExists(changeUserDto.getEmail()) != null)
			throw new ValidationException("Email Already Exists!");
	}
}
