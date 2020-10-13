package com.it.net.eureka.validator;

import com.it.net.eureka.dto.LoginMasterDto;
import com.it.net.eureka.dto.LoginUserDto;
import com.it.net.eureka.model.Master;
import com.it.net.eureka.model.User;
import com.it.net.eureka.utils.CryptoUtil;
import org.springframework.stereotype.Component;

import javax.validation.ValidationException;
import java.util.Arrays;

@Component
public class LoginMasterValidator extends MasterValidator {

    public Master validate(LoginMasterDto loginMasterDto) {
        Master master = super.checkIfAlreadyExists(loginMasterDto.getInputField());
        if (master == null) throw new ValidationException("Username/Email does not exists!");
        return doLogin(loginMasterDto, master);
    }

    public Master doLogin(LoginMasterDto loginMasterDto, Master master) {
        if (Arrays.equals(master.getMasterHashPassword(), CryptoUtil.generateHashWithGivenSalt(
                loginMasterDto.getPassword(), master.getMasterSaltPassword())))
            return master;
        else
            throw new ValidationException("Email/Username and Password are wrong!");
    }
}
