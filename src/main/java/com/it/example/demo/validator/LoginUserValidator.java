package com.it.example.demo.validator;

import java.util.Arrays;

import javax.validation.ValidationException;

import org.springframework.stereotype.Component;

import com.it.example.demo.dto.LoginUserDto;
import com.it.example.demo.model.User;
import com.it.example.demo.utils.CryptoUtil;

@Component
public class LoginUserValidator extends UserValidator{
	
	public User validate(LoginUserDto loginUserDto, User user) {
		user = super.checkIfAlreadyExists(loginUserDto.getInputField());
		if(user == null) throw new ValidationException("Username/Email does not exists!");
		return doLogin(loginUserDto, user);
		
	}
	
	public User doLogin(LoginUserDto loginUserDto, User user) {
		if(loginUserDto.getPassword().equals(loginUserDto.getConfirmPassword())) {
				if(Arrays.equals(user.getHashPassword(), CryptoUtil.generateHashWithGivenSalt(
								loginUserDto.getPassword(), user.getSaltPassword()))) {
					return user;
				} else {
					throw new ValidationException("Email/Username and Password are wrong!");
				}
				
		} else {
			throw new ValidationException("Password's confirm field is wrong!");
		}
	}
}
