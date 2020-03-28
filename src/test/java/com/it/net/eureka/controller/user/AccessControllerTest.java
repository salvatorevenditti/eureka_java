package com.it.net.eureka.controller.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.it.net.eureka.model.user.User;
import com.it.net.eureka.service.user.UserService;

@ExtendWith(MockitoExtension.class)
public class AccessControllerTest {
	
	@Mock
	private User user;

	@Mock
	private UserService userService;
	
	@InjectMocks
	private AccessController accessController;

	@BeforeEach
	protected void setUp() throws Exception {
	}

	@Test
	public final void testSignUp() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testLogIn() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testChangePassword() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testChangeEmail() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testChangeUsername() throws Exception {
		throw new RuntimeException("not yet implemented");
	}
}
