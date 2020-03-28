package com.it.net.eureka.service.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.it.net.eureka.model.user.User;
import com.it.net.eureka.repo.user.UserRepository;
import com.it.net.eureka.validator.user.ChangeUserValidator;
import com.it.net.eureka.validator.user.CreateUserValidator;
import com.it.net.eureka.validator.user.LoginUserValidator;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	
	@Mock
	private ChangeUserValidator changeUserValidator;

	@Mock
	private CreateUserValidator createUserValidator;

	@Mock
	private LoginUserValidator loginUserValidator;

	@Mock
	private User user;

	@Mock
	private UserRepository userRepo;
	
	@InjectMocks
	private UserService userService;

	@BeforeEach
	protected void setUp() throws Exception {
	}

	@Test
	public final void testCreateUser() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testLoginUser() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testChangePassword() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testChangeUsername() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testChangeEmail() throws Exception {
		throw new RuntimeException("not yet implemented");
	}
}
