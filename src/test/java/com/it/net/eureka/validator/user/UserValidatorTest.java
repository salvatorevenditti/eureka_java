package com.it.net.eureka.validator.user;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import javax.validation.ValidationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.it.net.eureka.model.user.User;
import com.it.net.eureka.repo.user.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserValidatorTest {

	private static final String STR_PWD_TRUE = "TEST89.ita";
	private static final String STR_PWD_FALSE = "TEST";
	private static final String STR_USERNAME = "TEST";
	private static final String STR_EMAIL = "TESTEST@GMAIL.COM";
	
	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserValidator userValidator;

	@Mock
	private User user;

	@BeforeEach
	protected void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test()
	public final void testCheckIfAlreadyExistsStringStringTrue() throws Exception {
		when(userRepository.findIfExistsByUsername(STR_USERNAME)).thenReturn(true);
		when(userRepository.findIfExistsByEmail(STR_EMAIL)).thenReturn(true);
		assertThrows(ValidationException.class, () -> userValidator.checkIfAlreadyExists(STR_USERNAME, STR_EMAIL));
	}

	@Test
	public final void testCheckIfAlreadyExistsStringStringFalse() throws Exception {
		when(userRepository.findIfExistsByUsername(STR_USERNAME)).thenReturn(false);
		when(userRepository.findIfExistsByEmail(STR_EMAIL)).thenReturn(false);
		assertDoesNotThrow( () -> userValidator.checkIfAlreadyExists(STR_USERNAME, STR_EMAIL));
	}

	@Test
	public final void testCheckIfAlreadyExistsStringTrue() throws Exception {
		given(userValidator.checkIfAlreadyExists(STR_EMAIL)).willReturn(user);
		assertNotNull(userValidator.checkIfAlreadyExists(STR_EMAIL));
		given(userValidator.checkIfAlreadyExists(STR_USERNAME)).willReturn(user);
		assertNotNull(userValidator.checkIfAlreadyExists(STR_USERNAME));
	}

	@Test
	public final void testCheckPswTrue() throws Exception {
		assertEquals(true, userValidator.checkPsw(STR_PWD_TRUE)); 
	}
	
	@Test
	public final void testCheckPswFalse() throws Exception {
		assertEquals(false, userValidator.checkPsw(STR_PWD_FALSE)); 
	}
}
