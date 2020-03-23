package com.it.net.eureka.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it.net.eureka.dto.user.ChangeUserDto;
import com.it.net.eureka.dto.user.CreateUserDto;
import com.it.net.eureka.dto.user.LoginUserDto;
import com.it.net.eureka.model.user.User;
import com.it.net.eureka.repo.user.UserRepository;
import com.it.net.eureka.utils.CryptoUtil;
import com.it.net.eureka.validator.user.ChangeUserValidator;
import com.it.net.eureka.validator.user.CreateUserValidator;
import com.it.net.eureka.validator.user.LoginUserValidator;

import javassist.NotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private CreateUserValidator createUserValidator;

	@Autowired
	private LoginUserValidator loginUserValidator;

	@Autowired
	private ChangeUserValidator changeUserValidator;

	public User createUser(CreateUserDto createUserDto, User user) {
		createUserValidator.validate(createUserDto);
		user.mapEntity(createUserDto);
		user = userRepo.save(user);
		return user;
	}

	public User loginUser(LoginUserDto loginUserDto, User user) {
		return loginUserValidator.validate(loginUserDto, user);
	}

	public User changePassword(ChangeUserDto changeUserDto, User user) throws NotFoundException {
		changeUserValidator.validateNewPassword(changeUserDto);
		user = userRepo.findByUsername(changeUserDto.getUsername());
		if(user == null) 
			throw new NotFoundException("Username doesn't exists!");
		if(!user.equals(userRepo.findByEmail(changeUserDto.getEmail())))
			throw new NotFoundException("Email doesn't exists!");
		user.setSaltPassword(CryptoUtil.generateSalt());
		user.setHashPassword(CryptoUtil.generateHashWithGivenSalt(changeUserDto.getPassword(), user.getSaltPassword()));
		return user = userRepo.save(user);	
	}

	public User changeUsername(ChangeUserDto changeUserDto, User user) throws NotFoundException {
		changeUserValidator.validateNewUsername(changeUserDto);
		user = userRepo.findByEmail(changeUserDto.getEmail());
		if(user == null) 
			throw new NotFoundException("Email doesn't exists!");
		//TODO Implement Password check
		user.setUsername(changeUserDto.getUsername());
		return user = userRepo.save(user);
	}

	public User changeEmail(ChangeUserDto changeUserDto, User user) throws NotFoundException {
		changeUserValidator.validateNewEmail(changeUserDto);
		user = userRepo.findByUsername(changeUserDto.getUsername());
		if(user == null) 
			throw new NotFoundException("Username doesn't exists!");
		//TODO Implement Password check
		user.setEmail(changeUserDto.getEmail());
		return user = userRepo.save(user);
	}
}
