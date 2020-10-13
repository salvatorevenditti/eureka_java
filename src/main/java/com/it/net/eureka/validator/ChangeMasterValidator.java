package com.it.net.eureka.validator;

import com.it.net.eureka.dto.ChangeMasterDto;
import com.it.net.eureka.dto.ChangeUserDto;
import com.it.net.eureka.model.Master;
import com.it.net.eureka.model.User;
import com.it.net.eureka.utils.CryptoUtil;
import org.springframework.stereotype.Component;

import javax.validation.ValidationException;
import java.util.Arrays;

@Component
public class ChangeMasterValidator extends MasterValidator {

    public void validateNewUsername(ChangeMasterDto changeMasterDto) {
        if(!super.checkEmail(changeMasterDto.getEmail()))
            throw new ValidationException("Email's pattern is not correct!");
        if(super.checkIfAlreadyExists(changeMasterDto.getUsername()) != null)
            throw new ValidationException("Username Already Exists!");
    }

    public void validateNewPassword(ChangeMasterDto changeMasterDto) {
        if (!super.checkPsw(changeMasterDto.getNewPassword()))
            throw new ValidationException("Password's pattern is not correct!");
    }

    public void validateNewEmail(ChangeMasterDto changeMasterDto) {
        if(super.checkIfAlreadyExists(changeMasterDto.getEmail()) != null)
            throw new ValidationException("Email Already Exists!");
    }

    public void checkOldPassword(ChangeMasterDto changeMasterDto, Master master) {
        if (!Arrays.equals(master.getMasterHashPassword(), CryptoUtil.generateHashWithGivenSalt(
                changeMasterDto.getPassword(), master.getMasterSaltPassword())))
            throw new ValidationException("Email/Username and Password are wrong!");
    }
}
