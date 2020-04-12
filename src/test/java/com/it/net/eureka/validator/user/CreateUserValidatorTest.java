package com.it.net.eureka.validator.user;

import com.it.net.eureka.dto.user.CreateUserDto;
import com.it.net.eureka.repo.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ValidationException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class CreateUserValidatorTest {

    private static final String TEST_EMAIL_FALSE = "TEST";
    private static final String TEST_PWD_FALSE = "PWD";
    private static final String TEST_EMAIL_TRUE = "TestTest@gmail.com";
    private static final String TEST_PWD_TRUE = "Password.00";

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CreateUserValidator createUserValidator;

    private CreateUserDto createUserDto;

    @BeforeEach
    protected void setUp() throws Exception {
        createUserDto = new CreateUserDto();
    }

    @Test
    public final void testValidate() throws Exception {
        createUserDto.setEmail(TEST_EMAIL_TRUE);
        createUserDto.setPassword(TEST_PWD_TRUE);
        createUserDto.setConfirmPassword(TEST_PWD_TRUE);
        createUserValidator.validate(createUserDto);
    }

	@Test
	public final void testValidateInputFields() throws Exception {
        createUserDto.setEmail(TEST_EMAIL_FALSE);
        createUserDto.setPassword(TEST_PWD_FALSE);
        assertThrows(ValidationException.class, () -> createUserValidator.validateInputFields(createUserDto));

        createUserDto.setEmail(TEST_EMAIL_TRUE);
        createUserDto.setPassword(TEST_PWD_TRUE);
        createUserDto.setConfirmPassword(TEST_PWD_FALSE);
        assertThrows(ValidationException.class, () -> createUserValidator.validateInputFields(createUserDto));
    }
}
