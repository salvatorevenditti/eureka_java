package com.it.net.eureka.validator.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.it.net.eureka.repo.user.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserValidatorTest {
	
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private UserValidator userValidator;

	@BeforeEach
	protected void setUp() throws Exception {
	}

	@Test
	public final void testCheckIfAlreadyExistsStringString() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testCheckIfAlreadyExistsString() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testCheckPsw() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testCheckEmail() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testApplyRegExp() throws Exception {
		throw new RuntimeException("not yet implemented");
	}
}
