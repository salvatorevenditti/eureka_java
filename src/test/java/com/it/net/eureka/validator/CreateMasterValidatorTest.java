package com.it.net.eureka.validator;

import com.it.net.eureka.dto.CreateMasterDto;
import com.it.net.eureka.dto.CreateUserDto;
import com.it.net.eureka.repo.MasterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ValidationException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class CreateMasterValidatorTest {

    private static final String TEST_EMAIL_FALSE = "TEST";
    private static final String TEST_PWD_FALSE = "PWD";
    private static final String TEST_EMAIL_TRUE = "TestTest@gmail.com";
    private static final String TEST_PWD_TRUE = "Password.00";

    @Mock
    private MasterRepository masterRepository;

    @InjectMocks
    private CreateMasterValidator createMasterValidator;

    private CreateMasterDto createMasterDto;

    @BeforeEach
    protected void setUp() throws Exception {
        createMasterDto = new CreateMasterDto();
    }

    @Test
    public final void testValidate() throws Exception {
        createMasterDto.setEmail(TEST_EMAIL_TRUE);
        createMasterDto.setPassword(TEST_PWD_TRUE);
        createMasterDto.setConfirmPassword(TEST_PWD_TRUE);
        createMasterValidator.validate(createMasterDto);
    }
    @Test
    public final void testValidateInputFields() throws Exception {
        createMasterDto.setEmail(TEST_EMAIL_FALSE);
        createMasterDto.setPassword(TEST_PWD_FALSE);
        assertThrows(ValidationException.class, () -> createMasterValidator.validateInputFields(createMasterDto));

        createMasterDto.setEmail(TEST_EMAIL_TRUE);
        createMasterDto.setPassword(TEST_PWD_TRUE);
        createMasterDto.setConfirmPassword(TEST_PWD_FALSE);
        assertThrows(ValidationException.class, () -> createMasterValidator.validateInputFields(createMasterDto));
    }

}
