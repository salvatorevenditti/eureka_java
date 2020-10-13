package com.it.net.eureka.validator;

import com.it.net.eureka.dto.ChangeUserDto;
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
import static org.mockito.BDDMockito.when;

@ExtendWith(MockitoExtension.class)
public class ChangeUserValidatorTest {

	private static final String TEST_EMAIL_TRUE = "testtest@gmail.com";
	private static final String TEST_EMAIL_FALSE = "test";
	private static final String TEST_USERNAME = "TEST";
	private static final String TEST_PWD_TRUE = "TEST98.ita";
	private static final String TEST_PWD_FALSE = "TEST";
	private static final byte[] TEST_OLD = TEST_PWD_TRUE.getBytes();
	
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private ChangeUserValidator changeUserValidator;
	
	@Mock
	private User user;
	
	private ChangeUserDto changeUserDto;

	@BeforeEach
	protected void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		changeUserDto = new ChangeUserDto();
	}

	@Test
	public final void testValidateNewUsernameNoException() throws Exception {
		changeUserDto.setEmail(TEST_EMAIL_TRUE);
		changeUserDto.setUsername(TEST_USERNAME);
		assertDoesNotThrow( () -> changeUserValidator.validateNewUsername(changeUserDto));
	}
	
	@Test
	public final void testValidateNewUsernameExceptionUsername() throws Exception {
		changeUserDto.setEmail(TEST_EMAIL_TRUE);
		changeUserDto.setUsername(TEST_USERNAME);
		when(userRepository.findByUsername(TEST_USERNAME)).thenReturn(user);
		assertThrows(ValidationException.class, () -> changeUserValidator.validateNewUsername(changeUserDto));
	}
	
	@Test
	public final void testValidateNewUsernameExceptionEmail() throws Exception {
		changeUserDto.setEmail(TEST_EMAIL_FALSE);
		assertThrows(ValidationException.class, () -> changeUserValidator.validateNewUsername(changeUserDto));
	}

	@Test
	public final void testValidateNewPasswordException() throws Exception {
		changeUserDto.setNewPassword(TEST_PWD_FALSE);
		assertThrows(ValidationException.class, () -> changeUserValidator.validateNewPassword(changeUserDto));
	}
	
	@Test
	public final void testValidateNewPasswordNoException() throws Exception {
		changeUserDto.setNewPassword(TEST_PWD_TRUE);
		assertDoesNotThrow(() -> changeUserValidator.validateNewPassword(changeUserDto));
	}

	@Test
	public final void testValidateNewEmailNoException() throws Exception {
		changeUserDto.setEmail(TEST_EMAIL_FALSE);
		assertDoesNotThrow( () -> changeUserValidator.validateNewEmail(changeUserDto));
	}
	
	@Test
	public final void testValidateNewEmailException() throws Exception {
		changeUserDto.setEmail(TEST_EMAIL_TRUE);
		when(userRepository.findByEmail(TEST_EMAIL_TRUE)).thenReturn(user);
		assertThrows(ValidationException.class, () -> changeUserValidator.validateNewEmail(changeUserDto));
	}

	@Test
	public final void testCheckOldPasswordException() throws Exception {
		user = new User();
		changeUserDto = new ChangeUserDto();
		user.setUserHashPassword(TEST_OLD);
		user.setUserSaltPassword(TEST_OLD);
		changeUserDto.setPassword(TEST_PWD_FALSE);
		assertThrows(ValidationException.class, () -> changeUserValidator.checkOldPassword(changeUserDto, user));
	}
	
	@Test
	public final void testCheckOldPasswordNoException() throws Exception {
		user = new User();
		changeUserDto = new ChangeUserDto();
		user.setUserSaltPassword(CryptoUtil.generateSalt());
		user.setUserHashPassword(CryptoUtil.generateHashWithGivenSalt(TEST_PWD_TRUE, user.getUserSaltPassword()));
		changeUserDto.setPassword(TEST_PWD_TRUE);
		assertDoesNotThrow( () -> changeUserValidator.checkOldPassword(changeUserDto, user));
	}
}
