package com.it.net.eureka.validator;

import com.it.net.eureka.dto.LoginUserDto;
import com.it.net.eureka.model.User;
import com.it.net.eureka.repo.UserRepository;
import com.it.net.eureka.utils.CryptoUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ValidationException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class LoginUserValidatorTest {

	private static String TEST_INPUT = "TEST_INPUT";
	private static String TEST_PWD = "TEST_PWD";
	private static byte[] TEST_SALT = "TEST_SALT".getBytes();
	private static byte[] TEST_FAIL_HASH = "TEST_FAIL_HASH".getBytes();

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private LoginUserValidator loginUserValidator;

	@Mock
	private User user;

	@Mock
	private UserValidator userValidator;

	private LoginUserDto loginUserDto;

	@BeforeEach
	protected void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		loginUserDto = new LoginUserDto();
		user = new User();
		loginUserDto.setPassword(TEST_PWD);
		user.setUserSaltPassword(TEST_SALT);
		user.setUserHashPassword(CryptoUtil.generateHashWithGivenSalt(loginUserDto.getPassword(), user.getUserSaltPassword()));
	}

	@Test
	public final void testValidate() throws Exception {
        loginUserDto.setInputField(TEST_INPUT);
        given(loginUserValidator.checkIfAlreadyExists(loginUserDto.getInputField())).willReturn(user);
        assertDoesNotThrow(() -> loginUserValidator.validate(loginUserDto));
        given(loginUserValidator.checkIfAlreadyExists(loginUserDto.getInputField())).willReturn(user = null);
        assertThrows(ValidationException.class, () -> loginUserValidator.validate(loginUserDto));
    }

	@Test
	public final void testDoLogin() throws Exception {
		assertDoesNotThrow(() -> loginUserValidator.doLogin(loginUserDto, user));
		user.setUserHashPassword(TEST_FAIL_HASH);
		assertThrows(ValidationException.class, () -> loginUserValidator.doLogin(loginUserDto, user));
	}
}
