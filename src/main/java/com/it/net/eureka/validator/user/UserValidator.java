package com.it.net.eureka.validator.user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;

import com.it.net.eureka.model.user.User;
import com.it.net.eureka.repo.user.UserRepository;

public class UserValidator {

	private static final int DEFAULT = 0;
	private static final int CASE_INSENSITIVE = Pattern.CASE_INSENSITIVE;
	private static final String REG_EXP_EMAIL = "^[a-zA-Z0-9.!#$%&'*+\\=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";
	private static final String REG_EXP_SPEC_CHAR = "[^a-z0-9 ]";
	private static final String REG_EXP_NUMBER = "[\\d$]";
	private static final String REG_EXP_CPT_LETTER = "[A-Z]";

	@Autowired
	private UserRepository userRepository;

	public void checkIfAlreadyExists(String username, String email) {
		String message = null;
		if(userRepository.findIfExistsByUsername(username)) message = "Username Already Exists!";
		if(userRepository.findIfExistsByEmail(email)) message = message + " Email Already Exists!";
		if(message != null) throw new ValidationException(message);
	}

	public User checkIfAlreadyExists(String input) {
		if(checkEmail(input)) 
			return userRepository.findByEmail(input);
		else 
			return userRepository.findByUsername(input);
	}

	public boolean checkPsw(String pwd) {
		return (applyRegExp(REG_EXP_SPEC_CHAR, CASE_INSENSITIVE, pwd) 
				&& applyRegExp(REG_EXP_CPT_LETTER, DEFAULT, pwd) 
				&& applyRegExp(REG_EXP_NUMBER, DEFAULT, pwd)) ;
	}

	public boolean checkEmail(String email) {
		return applyRegExp(REG_EXP_EMAIL, DEFAULT, email);
	}

	public boolean applyRegExp(String regExp, int option, String toMatch) {
		Pattern p = null;
		switch(option) {
		case CASE_INSENSITIVE:
			p = Pattern.compile(regExp, Pattern.CASE_INSENSITIVE);
			break;
		default:
			p = Pattern.compile(regExp);
			break;
		}
		Matcher m = p.matcher(toMatch);
		return m.find();
	}
}
