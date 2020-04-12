package com.it.net.eureka.validator.user;

import com.it.net.eureka.dto.user.LoginUserDto;
import com.it.net.eureka.model.user.User;
import com.it.net.eureka.utils.CryptoUtil;
import org.springframework.stereotype.Component;

import javax.validation.ValidationException;
import java.util.Arrays;

@Component
public class LoginUserValidator extends UserValidator {

    public User validate(LoginUserDto loginUserDto, User user) {
        user = super.checkIfAlreadyExists(loginUserDto.getInputField());
        if (user == null) throw new ValidationException("Username/Email does not exists!");
        return doLogin(loginUserDto, user);
    }

    public User doLogin(LoginUserDto loginUserDto, User user) {
        if (Arrays.equals(user.getHashPassword(), CryptoUtil.generateHashWithGivenSalt(
                loginUserDto.getPassword(), user.getSaltPassword())))
            return user;
        else
            throw new ValidationException("Email/Username and Password are wrong!");
    }
}
