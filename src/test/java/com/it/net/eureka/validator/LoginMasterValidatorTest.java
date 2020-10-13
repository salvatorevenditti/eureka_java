package com.it.net.eureka.validator;

import com.it.net.eureka.dto.LoginMasterDto;
import com.it.net.eureka.dto.LoginUserDto;
import com.it.net.eureka.model.Master;
import com.it.net.eureka.model.User;
import com.it.net.eureka.repo.MasterRepository;
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
public class LoginMasterValidatorTest {

    private static String TEST_INPUT = "TEST_INPUT";
    private static String TEST_PWD = "TEST_PWD";
    private static byte[] TEST_SALT = "TEST_SALT".getBytes();
    private static byte[] TEST_FAIL_HASH = "TEST_FAIL_HASH".getBytes();

    @Mock
    private MasterRepository masterRepository;

    @InjectMocks
    private LoginMasterValidator loginMasterValidator;

    @Mock
    private Master master;

    private LoginMasterDto loginMasterDto;

    @BeforeEach
    protected void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        loginMasterDto = new LoginMasterDto();
        master = new Master();
        loginMasterDto.setPassword(TEST_PWD);
        master.setMasterSaltPassword(TEST_SALT);
        master.setMasterHashPassword(CryptoUtil.generateHashWithGivenSalt(loginMasterDto.getPassword(), master.getMasterSaltPassword()));
    }

    @Test
    public final void testValidate() throws Exception {
        loginMasterDto.setInputField(TEST_INPUT);
        given(loginMasterValidator.checkIfAlreadyExists(loginMasterDto.getInputField())).willReturn(master);
        assertDoesNotThrow(() -> loginMasterValidator.validate(loginMasterDto));
        given(loginMasterValidator.checkIfAlreadyExists(loginMasterDto.getInputField())).willReturn(master = null);
        assertThrows(ValidationException.class, () -> loginMasterValidator.validate(loginMasterDto));
    }

    @Test
    public final void testDoLogin() throws Exception {
        assertDoesNotThrow(() -> loginMasterValidator.doLogin(loginMasterDto, master));
        master.setMasterHashPassword(TEST_FAIL_HASH);
        assertThrows(ValidationException.class, () -> loginMasterValidator.doLogin(loginMasterDto, master));
    }
}
