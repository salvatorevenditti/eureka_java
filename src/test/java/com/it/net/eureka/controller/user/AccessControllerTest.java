package com.it.net.eureka.controller.user;

import com.it.net.eureka.dto.user.ChangeUserDto;
import com.it.net.eureka.dto.user.CreateUserDto;
import com.it.net.eureka.dto.user.LoginUserDto;
import com.it.net.eureka.model.user.User;
import com.it.net.eureka.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class AccessControllerTest {

	@Mock
	private User user;

	@Mock
	private UserService userService;

	@InjectMocks
	private AccessController accessController;

	private CreateUserDto createUserDto;
	private LoginUserDto loginUserDto;
	private ChangeUserDto changeUserDto;

	@BeforeEach
	protected void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public final void testSignUp() throws Exception {
		given(userService.createUser(createUserDto)).willReturn(user);
		assertNotNull(accessController.signUp(createUserDto));
	}

	@Test
	public final void testLogIn() throws Exception {
		given(userService.loginUser(loginUserDto)).willReturn(user);
		assertNotNull(accessController.logIn(loginUserDto));
	}

	@Test
	public final void testChangePassword() throws Exception {
		given(userService.changePassword(changeUserDto)).willReturn(user);
		assertNotNull(accessController.changePassword(changeUserDto));
	}

	@Test
	public final void testChangeEmail() throws Exception {
		given(userService.changeEmail(changeUserDto)).willReturn(user);
		assertNotNull(accessController.changeEmail(changeUserDto));
	}

	@Test
	public final void testChangeUsername() throws Exception {
		given(userService.changeUsername(changeUserDto)).willReturn(user);
		assertNotNull(accessController.changeUsername(changeUserDto));
	}
}
