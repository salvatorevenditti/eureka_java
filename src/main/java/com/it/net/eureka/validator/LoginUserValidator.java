package com.it.net.eureka.validator;

import com.it.net.eureka.dto.LoginUserDto;
import com.it.net.eureka.model.User;
import com.it.net.eureka.utils.CryptoUtil;
import org.springframework.stereotype.Component;

import javax.validation.ValidationException;
import java.util.Arrays;

@Component
public class LoginUserValidator extends UserValidator {

    public User validate(LoginUserDto loginUserDto) {
        User user = super.checkIfAlreadyExists(loginUserDto.getInputField());
        if (user == null) throw new ValidationException("Username/Email does not exists!");
        return doLogin(loginUserDto, user);
    }

    public User doLogin(LoginUserDto loginUserDto, User user) {
        if (Arrays.equals(user.getUserHashPassword(), CryptoUtil.generateHashWithGivenSalt(
                loginUserDto.getPassword(), user.getUserSaltPassword())))
            return user;
        else
            throw new ValidationException("Email/Username and Password are wrong!");
    }
}
