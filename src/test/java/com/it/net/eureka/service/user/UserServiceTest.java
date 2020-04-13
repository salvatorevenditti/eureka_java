package com.it.net.eureka.service.user;

import com.it.net.eureka.dto.user.ChangeUserDto;
import com.it.net.eureka.dto.user.CreateUserDto;
import com.it.net.eureka.dto.user.LoginUserDto;
import com.it.net.eureka.model.user.User;
import com.it.net.eureka.repo.user.UserRepository;
import com.it.net.eureka.utils.CryptoUtil;
import com.it.net.eureka.validator.user.ChangeUserValidator;
import com.it.net.eureka.validator.user.CreateUserValidator;
import com.it.net.eureka.validator.user.LoginUserValidator;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private static String TEST_EMAIL = "testtest00@gmail.com";
    private static String TEST_USERNAME = "testUser00";
    private static String TEST_PWD = "Test00.com";
    private static String TEST_CREATE_PWD_CONFIRM_TRUE = "Test00.com";
    private static byte[] TEST_SALT = "TEST_SALT".getBytes();

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

    private CreateUserDto createUserDto;
    private LoginUserDto loginUserDto;
    private ChangeUserDto changeUserDto;

    @BeforeEach
    protected void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        createUserDto = new CreateUserDto();
        loginUserDto = new LoginUserDto();
        changeUserDto = new ChangeUserDto();
    }

	@Test
	public final void testCreateUser() throws Exception {
        createUserDto.setUsername(TEST_USERNAME);
        createUserDto.setEmail(TEST_EMAIL);
        createUserDto.setPassword(TEST_PWD);
        createUserDto.setConfirmPassword(TEST_CREATE_PWD_CONFIRM_TRUE);
        given(userRepo.save(user)).willReturn(user);
        assertNotNull(userService.createUser(createUserDto));
    }

	@Test
	public final void testLoginUser() throws Exception {
        given(loginUserValidator.validate(loginUserDto)).willReturn(user);
        assertNotNull(userService.loginUser(loginUserDto));
    }

	@Test
	public final void testChangePassword() throws Exception {
        user = new User();
        changeUserDto.setUsername(TEST_USERNAME);
        changeUserDto.setPassword(TEST_PWD);
        given(userRepo.findByUsername(changeUserDto.getUsername())).willReturn(user);
        given(userRepo.findByEmail(user.getEmail())).willReturn(user);
        changeUserDto.setPassword(TEST_PWD);
        user.setSaltPassword(TEST_SALT);
        user.setHashPassword(CryptoUtil.generateHashWithGivenSalt(changeUserDto.getPassword(), user.getSaltPassword()));
        changeUserDto.setNewPassword(changeUserDto.getPassword());
        given(userRepo.save(user)).willReturn(user);
        assertNotNull(userService.changePassword(changeUserDto));

        given(userRepo.findByEmail(changeUserDto.getEmail())).willReturn(user = null);
        assertThrows(NotFoundException.class, () -> userService.changePassword(changeUserDto));

        given(userRepo.findByUsername(changeUserDto.getUsername())).willReturn(user = null);
        assertThrows(NotFoundException.class, () -> userService.changePassword(changeUserDto));
    }

	@Test
	public final void testChangeUsername() throws Exception {
        user = new User();
        changeUserDto.setUsername(TEST_USERNAME);
        changeUserDto.setEmail(TEST_EMAIL);
        given(userRepo.findByEmail(changeUserDto.getEmail())).willReturn(user);
        changeUserDto.setPassword(TEST_PWD);
        user.setSaltPassword(TEST_SALT);
        user.setHashPassword(CryptoUtil.generateHashWithGivenSalt(changeUserDto.getPassword(), user.getSaltPassword()));
        changeUserDto.setNewPassword(changeUserDto.getPassword());
        given(userRepo.save(user)).willReturn(user);
        assertNotNull(userService.changeUsername(changeUserDto));

        given(userRepo.findByEmail(changeUserDto.getEmail())).willReturn(user = null);
        assertThrows(NotFoundException.class, () -> userService.changeUsername(changeUserDto));
    }

	@Test
	public final void testChangeEmail() throws Exception {
        user = new User();
        changeUserDto.setUsername(TEST_USERNAME);
        given(userRepo.findByUsername(changeUserDto.getUsername())).willReturn(user);
        changeUserDto.setPassword(TEST_PWD);
        user.setSaltPassword(TEST_SALT);
        user.setHashPassword(CryptoUtil.generateHashWithGivenSalt(changeUserDto.getPassword(), user.getSaltPassword()));
        changeUserDto.setNewPassword(changeUserDto.getPassword());
        given(userRepo.save(user)).willReturn(user);
        assertNotNull(userService.changeEmail(changeUserDto));

        given(userRepo.findByUsername(changeUserDto.getUsername())).willReturn(user = null);
        assertThrows(NotFoundException.class, () -> userService.changeEmail(changeUserDto));

    }
}
