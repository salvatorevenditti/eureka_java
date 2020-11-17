package com.it.net.eureka.validator;

import com.it.net.eureka.dto.ChangeUserDto;
import com.it.net.eureka.model.User;
import com.it.net.eureka.utils.CryptoUtil;
import org.springframework.stereotype.Component;

import javax.validation.ValidationException;
import java.util.Arrays;

@Component
public class ChangeUserValidator extends UserValidator {

	public void validateNewUsername(ChangeUserDto changeUserDto) {
		if(!super.checkEmail(changeUserDto.getEmail())) 
			throw new ValidationException("Email's pattern is not correct!");
		if(super.checkIfAlreadyExists(changeUserDto.getUsername()) != null)
			throw new ValidationException("Username Already Exists!");
	}

	public void validateNewPassword(ChangeUserDto changeUserDto) {
		if (!super.checkPsw(changeUserDto.getNewPassword()))
			throw new ValidationException("Password's pattern is not correct!");
	}

	public void validateNewEmail(ChangeUserDto changeUserDto) {
		if(super.checkIfAlreadyExists(changeUserDto.getEmail()) != null)
			throw new ValidationException("Email Already Exists!");
	}

	public void checkOldPassword(ChangeUserDto changeUserDto, User user) {
		if (!Arrays.equals(user.getUserHashPassword(), CryptoUtil.generateHashWithGivenSalt(
				changeUserDto.getPassword(), user.getUserSaltPassword())))
			throw new ValidationException("Email/Username and Password are wrong!");
	}
}