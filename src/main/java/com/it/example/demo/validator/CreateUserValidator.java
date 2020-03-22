package com.it.example.demo.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.it.example.demo.dto.CreateUserDto;
import com.it.example.demo.repo.UserRepository;

@Component
public class CreateUserValidator {

	private static final int DEFAULT = 0;
	private static final int CASE_INSENSITIVE = Pattern.CASE_INSENSITIVE;
	private static final String REG_EXP_EMAIL = "^[a-zA-Z0-9.!#$%&'*+\\=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";
	private static final String REG_EXP_SPEC_CHAR = "[^a-z0-9 ]";
	private static final String REG_EXP_NUMBER = "[\\d$]";
	private static final String REG_EXP_CPT_LETTER = "[A-Z]";
	
	@Autowired
	private UserRepository userRepository;
	
	public void validate(CreateUserDto createUserDto) throws ValidationException {
		validateInputFields(createUserDto.getEmail(), createUserDto.getPassword());
		checkIfAlreadyExists(createUserDto.getUsername(), createUserDto.getEmail());
	}
	
	public void checkIfAlreadyExists(String username, String email) {
		
		String message = null;
		
		if(userRepository.findByUsername(username)) message = "Username Already Exists!";
		if(userRepository.findByEmail(email)) message = message + " Email Already Exists!";
		if(message != null) throw new ValidationException(message);
	}
	
	public void validateInputFields(String email, String pwd) {
		
		if(!checkEmail(email) || !checkPsw(pwd)) {
			throw new ValidationException("Check your email and password!\n"
					+ "ExamplePassword --> Example.00\n"
					+ "ExampleEmail --> examplemail@gmail.com");
		}
	}
	
	public boolean checkEmail(String email) {
		return applyRegExp(REG_EXP_EMAIL, DEFAULT, email);
	}
	
	public boolean checkPsw(String pwd) {
		return (applyRegExp(REG_EXP_SPEC_CHAR, CASE_INSENSITIVE, pwd) 
				&& applyRegExp(REG_EXP_CPT_LETTER, DEFAULT, pwd) 
				&& applyRegExp(REG_EXP_NUMBER, DEFAULT, pwd)) ;
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
