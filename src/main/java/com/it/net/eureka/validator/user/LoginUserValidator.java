package com.it.net.eureka.validator.user;

import java.util.Arrays;

import javax.validation.ValidationException;

import org.springframework.stereotype.Component;

import com.it.net.eureka.dto.user.LoginUserDto;
import com.it.net.eureka.model.user.User;
import com.it.net.eureka.utils.CryptoUtil;

@Component
public class LoginUserValidator extends UserValidator {

	private User user;
	
	public User validate(LoginUserDto loginUserDto) {
		user = super.checkIfAlreadyExists(loginUserDto.getInputField());
		if(user == null) throw new ValidationException("Username/Email does not exists!");
		return doLogin(loginUserDto);
	}

	public User doLogin(LoginUserDto loginUserDto) {
		if(Arrays.equals(user.getHashPassword(), CryptoUtil.generateHashWithGivenSalt(
				loginUserDto.getPassword(), user.getSaltPassword())))
			return user;
		else
			throw new ValidationException("Email/Username and Password are wrong!");
	}
}
