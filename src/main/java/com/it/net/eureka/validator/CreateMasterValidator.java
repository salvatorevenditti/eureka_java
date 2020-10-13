package com.it.net.eureka.validator;

import com.it.net.eureka.dto.CreateMasterDto;
import com.it.net.eureka.dto.CreateUserDto;
import org.springframework.stereotype.Component;

import javax.validation.ValidationException;

@Component
public class CreateMasterValidator extends MasterValidator {

    public void validate(CreateMasterDto createMasterDto) {
        validateInputFields(createMasterDto);
        super.checkIfAlreadyExists(createMasterDto.getUsername(), createMasterDto.getEmail());
    }

    public void validateInputFields(CreateMasterDto createMasterDto) {
        if(!super.checkEmail(createMasterDto.getEmail()) || !super.checkPsw(createMasterDto.getPassword())) {
            throw new ValidationException("Check your email and password!\n"
                    + "ExamplePassword --> Example.00\n"
                    + "ExampleEmail --> examplemail@gmail.com");
        } else if(!createMasterDto.getPassword().equals(createMasterDto.getConfirmPassword())) {
            throw new ValidationException("Password and ConfirmPassword fields do not match!");
        }
    }

}
