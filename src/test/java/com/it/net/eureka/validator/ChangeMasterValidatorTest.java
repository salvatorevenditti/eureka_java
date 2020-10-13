package com.it.net.eureka.validator;

import com.it.net.eureka.dto.ChangeMasterDto;
import com.it.net.eureka.dto.ChangeMasterDto;
import com.it.net.eureka.dto.CreateMasterDto;
import com.it.net.eureka.model.Master;
import com.it.net.eureka.model.Master;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChangeMasterValidatorTest {
    
    private static final String TEST_EMAIL_TRUE = "testtest@gmail.com";
    private static final String TEST_EMAIL_FALSE = "test";
    private static final String TEST_USERNAME = "TEST";
    private static final String TEST_PWD_TRUE = "TEST98.ita";
    private static final String TEST_PWD_FALSE = "TEST";
    private static final byte[] TEST_OLD = TEST_PWD_TRUE.getBytes();

    @Mock
    private MasterRepository masterRepository;

    @InjectMocks
    private ChangeMasterValidator changeMasterValidator;
    
    @Mock
    private Master master;

    private ChangeMasterDto changeMasterDto;

    @BeforeEach
    protected void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        changeMasterDto = new ChangeMasterDto();
    }

    @Test
    public final void testValidateNewUsernameNoException() throws Exception {
        changeMasterDto.setEmail(TEST_EMAIL_TRUE);
        changeMasterDto.setUsername(TEST_USERNAME);
        assertDoesNotThrow( () -> changeMasterValidator.validateNewUsername(changeMasterDto));
    }

    @Test
    public final void testValidateNewUsernameExceptionUsername() throws Exception {
        changeMasterDto.setEmail(TEST_EMAIL_TRUE);
        changeMasterDto.setUsername(TEST_USERNAME);
        when(masterRepository.findByUsername(TEST_USERNAME)).thenReturn(master);
        assertThrows(ValidationException.class, () -> changeMasterValidator.validateNewUsername(changeMasterDto));
    }

    @Test
    public final void testValidateNewUsernameExceptionEmail() throws Exception {
        changeMasterDto.setEmail(TEST_EMAIL_FALSE);
        assertThrows(ValidationException.class, () -> changeMasterValidator.validateNewUsername(changeMasterDto));
    }

    @Test
    public final void testValidateNewPasswordException() throws Exception {
        changeMasterDto.setNewPassword(TEST_PWD_FALSE);
        assertThrows(ValidationException.class, () -> changeMasterValidator.validateNewPassword(changeMasterDto));
    }

    @Test
    public final void testValidateNewPasswordNoException() throws Exception {
        changeMasterDto.setNewPassword(TEST_PWD_TRUE);
        assertDoesNotThrow(() -> changeMasterValidator.validateNewPassword(changeMasterDto));
    }

    @Test
    public final void testValidateNewEmailNoException() throws Exception {
        changeMasterDto.setEmail(TEST_EMAIL_FALSE);
        assertDoesNotThrow( () -> changeMasterValidator.validateNewEmail(changeMasterDto));
    }

    @Test
    public final void testValidateNewEmailException() throws Exception {
        changeMasterDto.setEmail(TEST_EMAIL_TRUE);
        when(masterRepository.findByEmail(TEST_EMAIL_TRUE)).thenReturn(master);
        assertThrows(ValidationException.class, () -> changeMasterValidator.validateNewEmail(changeMasterDto));
    }

    @Test
    public final void testCheckOldPasswordException() throws Exception {
        master = new Master();
        changeMasterDto = new ChangeMasterDto();
        master.setMasterHashPassword(TEST_OLD);
        master.setMasterSaltPassword(TEST_OLD);
        changeMasterDto.setPassword(TEST_PWD_FALSE);
        assertThrows(ValidationException.class, () -> changeMasterValidator.checkOldPassword(changeMasterDto, master));
    }

    @Test
    public final void testCheckOldPasswordNoException() throws Exception {
        master = new Master();
        changeMasterDto = new ChangeMasterDto();
        master.setMasterSaltPassword(CryptoUtil.generateSalt());
        master.setMasterHashPassword(CryptoUtil.generateHashWithGivenSalt(TEST_PWD_TRUE, master.getMasterSaltPassword()));
        changeMasterDto.setPassword(TEST_PWD_TRUE);
        assertDoesNotThrow( () -> changeMasterValidator.checkOldPassword(changeMasterDto, master));
    }
}
