package com.it.net.eureka.validator.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.it.net.eureka.repo.user.UserRepository;

@ExtendWith(MockitoExtension.class)
public class ChangeUserValidatorTest {

	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private ChangeUserValidator changeUserValidator;

	@BeforeEach
	protected void setUp() throws Exception {
	}

	@Test
	public final void testValidateNewUsername() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testValidateNewPassword() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testValidateNewEmail() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testCheckOldPassword() throws Exception {
		throw new RuntimeException("not yet implemented");
	}
}
