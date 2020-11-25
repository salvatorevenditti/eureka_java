package com.it.net.eureka.service;

import com.it.net.eureka.dto.ChangeUserDto;
import com.it.net.eureka.dto.CreateUserDto;
import com.it.net.eureka.dto.LoginUserDto;
import com.it.net.eureka.model.User;
import com.it.net.eureka.repo.UserRepository;
import com.it.net.eureka.utils.CryptoUtil;
import com.it.net.eureka.validator.ChangeUserValidator;
import com.it.net.eureka.validator.CreateUserValidator;
import com.it.net.eureka.validator.LoginUserValidator;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

	private User user = new User();

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
		if (user == null)
			throw new NotFoundException("Username doesn't exists!");
		if (!user.equals(userRepo.findByEmail(changeUserDto.getEmail())))
			throw new NotFoundException("Email doesn't exists!");
		changeUserValidator.checkOldPassword(changeUserDto, user);
		user.setUserSaltPassword(CryptoUtil.generateSalt());
		user.setUserHashPassword(CryptoUtil.generateHashWithGivenSalt(changeUserDto.getNewPassword(), user.getUserSaltPassword()));
		return userRepo.save(user);
	}

	public User changeUsername(ChangeUserDto changeUserDto) throws NotFoundException {
		changeUserValidator.validateNewUsername(changeUserDto);
		user = userRepo.findByEmail(changeUserDto.getEmail());
		if (user == null)
			throw new NotFoundException("Email doesn't exists!");
		changeUserValidator.checkOldPassword(changeUserDto, user);
		user.setUserUsername(changeUserDto.getUsername());
		return userRepo.save(user);
	}

	public User changeEmail(ChangeUserDto changeUserDto) throws NotFoundException {
		changeUserValidator.validateNewEmail(changeUserDto);
		user = userRepo.findByUsername(changeUserDto.getUsername());
		if (user == null)
			throw new NotFoundException("Username doesn't exists!");
		changeUserValidator.checkOldPassword(changeUserDto, user);
        user.setUserEmail(changeUserDto.getEmail());
        return userRepo.save(user);
    }

    public User getUser(String correlationId) throws NotFoundException {
        Optional<User> userOpt = userRepo.findByCorrelationId(correlationId);
        user = userOpt.get();
        if (user == null) throw new NotFoundException("User " + correlationId + "not found! ");
        return user;
    }

    public User deleteUser(String correlationId) throws NotFoundException {
        Optional<User> userOpt = userRepo.findByCorrelationId(correlationId);
        user = userOpt.get();
        if (user == null) throw new NotFoundException("User " + correlationId + "not found! ");
        userRepo.delete(user);
        return user;
    }
}
