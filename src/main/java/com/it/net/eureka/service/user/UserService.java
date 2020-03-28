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
	
	private User user;

	public User createUser(CreateUserDto createUserDto) {
		createUserValidator.validate(createUserDto);
		user.mapEntity(createUserDto);
		user = userRepo.save(user);
		return user;
	}

	public User loginUser(LoginUserDto loginUserDto) {
		return loginUserValidator.validate(loginUserDto);
	}

	public User changePassword(ChangeUserDto changeUserDto) throws NotFoundException {
		changeUserValidator.validateNewPassword(changeUserDto);
		user = userRepo.findByUsername(changeUserDto.getUsername());
		if(user == null) 
			throw new NotFoundException("Username doesn't exists!");
		if(!user.equals(userRepo.findByEmail(changeUserDto.getEmail())))
			throw new NotFoundException("Email doesn't exists!");
		changeUserValidator.checkOldPassword(changeUserDto, user);
		user.setSaltPassword(CryptoUtil.generateSalt());
		user.setHashPassword(CryptoUtil.generateHashWithGivenSalt(changeUserDto.getNewPassword(), user.getSaltPassword()));
		return userRepo.save(user);	
	}

	public User changeUsername(ChangeUserDto changeUserDto) throws NotFoundException {
		changeUserValidator.validateNewUsername(changeUserDto);
		user = userRepo.findByEmail(changeUserDto.getEmail());
		if(user == null) 
			throw new NotFoundException("Email doesn't exists!");
		changeUserValidator.checkOldPassword(changeUserDto, user);
		user.setUsername(changeUserDto.getUsername());
		return userRepo.save(user);
	}

	public User changeEmail(ChangeUserDto changeUserDto) throws NotFoundException {
		changeUserValidator.validateNewEmail(changeUserDto);
		user = userRepo.findByUsername(changeUserDto.getUsername());
		if(user == null) 
			throw new NotFoundException("Username doesn't exists!");
		changeUserValidator.checkOldPassword(changeUserDto, user);
		user.setEmail(changeUserDto.getEmail());
		return userRepo.save(user);
	}
}
