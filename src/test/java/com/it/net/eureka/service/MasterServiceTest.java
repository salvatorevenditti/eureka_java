package com.it.net.eureka.service;

import com.it.net.eureka.dto.*;
import com.it.net.eureka.model.Master;
import com.it.net.eureka.model.Master;
import com.it.net.eureka.model.Master;
import com.it.net.eureka.model.User;
import com.it.net.eureka.repo.MasterRepository;
import com.it.net.eureka.utils.CryptoUtil;
import com.it.net.eureka.validator.ChangeMasterValidator;
import com.it.net.eureka.validator.CreateMasterValidator;
import com.it.net.eureka.validator.LoginMasterValidator;
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
public class MasterServiceTest {

    private static String TEST_EMAIL = "testtest00@gmail.com";
    private static String TEST_USERNAME = "testMaster00";
    private static String TEST_PWD = "Test00.com";
    private static String TEST_CREATE_PWD_CONFIRM_TRUE = "Test00.com";
    private static byte[] TEST_SALT = "TEST_SALT".getBytes();

    @Mock
    private ChangeMasterValidator changeMasterValidator;

    @Mock
    private CreateMasterValidator createMasterValidator;

    @Mock
    private LoginMasterValidator loginMasterValidator;

    @Mock
    private Master master;
    
    @Mock
    private MasterRepository masterRepository;

    @InjectMocks
    private MasterService masterService;

    private ChangeMasterDto changeMasterDto;
    private CreateMasterDto createMasterDto;
    private LoginMasterDto loginMasterDto;

    @BeforeEach
    protected void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        changeMasterDto = new ChangeMasterDto();
        createMasterDto = new CreateMasterDto();
        loginMasterDto = new LoginMasterDto();
    }

    @Test
    public final void testCreateMaster() throws Exception {
        createMasterDto.setUsername(TEST_USERNAME);
        createMasterDto.setEmail(TEST_EMAIL);
        createMasterDto.setPassword(TEST_PWD);
        createMasterDto.setConfirmPassword(TEST_CREATE_PWD_CONFIRM_TRUE);
        given(masterRepository.save(master)).willReturn(master);
        assertNotNull(masterService.createMaster(createMasterDto));
    }

    @Test
    public final void testLoginMaster() throws Exception {
        given(loginMasterValidator.validate(loginMasterDto)).willReturn(master);
        assertNotNull(masterService.loginMaster(loginMasterDto));
    }

    @Test
    public final void testChangePassword() throws Exception {
        master = new Master();
        changeMasterDto.setUsername(TEST_USERNAME);
        changeMasterDto.setPassword(TEST_PWD);
        given(masterRepository.findByUsername(changeMasterDto.getUsername())).willReturn(master);
        given(masterRepository.findByEmail(master.getMasterEmail())).willReturn(master);
        changeMasterDto.setPassword(TEST_PWD);
        master.setMasterSaltPassword(TEST_SALT);
        master.setMasterHashPassword(CryptoUtil.generateHashWithGivenSalt(changeMasterDto.getPassword(), master.getMasterSaltPassword()));
        changeMasterDto.setNewPassword(changeMasterDto.getPassword());
        given(masterRepository.save(master)).willReturn(master);
        assertNotNull(masterService.changePassword(changeMasterDto));

        given(masterRepository.findByEmail(changeMasterDto.getEmail())).willReturn(master = null);
        assertThrows(NotFoundException.class, () -> masterService.changePassword(changeMasterDto));

        given(masterRepository.findByUsername(changeMasterDto.getUsername())).willReturn(master = null);
        assertThrows(NotFoundException.class, () -> masterService.changePassword(changeMasterDto));
    }

    @Test
    public final void testChangeUsername() throws Exception {
        master = new Master();
        changeMasterDto.setUsername(TEST_USERNAME);
        changeMasterDto.setEmail(TEST_EMAIL);
        given(masterRepository.findByEmail(changeMasterDto.getEmail())).willReturn(master);
        changeMasterDto.setPassword(TEST_PWD);
        master.setMasterSaltPassword(TEST_SALT);
        master.setMasterHashPassword(CryptoUtil.generateHashWithGivenSalt(changeMasterDto.getPassword(), master.getMasterSaltPassword()));
        changeMasterDto.setNewPassword(changeMasterDto.getPassword());
        given(masterRepository.save(master)).willReturn(master);
        assertNotNull(masterService.changeUsername(changeMasterDto));

        given(masterRepository.findByEmail(changeMasterDto.getEmail())).willReturn(master = null);
        assertThrows(NotFoundException.class, () -> masterService.changeUsername(changeMasterDto));
    }

    @Test
    public final void testChangeEmail() throws Exception {
        master = new Master();
        changeMasterDto.setUsername(TEST_USERNAME);
        given(masterRepository.findByUsername(changeMasterDto.getUsername())).willReturn(master);
        changeMasterDto.setPassword(TEST_PWD);
        master.setMasterSaltPassword(TEST_SALT);
        master.setMasterHashPassword(CryptoUtil.generateHashWithGivenSalt(changeMasterDto.getPassword(), master.getMasterSaltPassword()));
        changeMasterDto.setNewPassword(changeMasterDto.getPassword());
        given(masterRepository.save(master)).willReturn(master);
        assertNotNull(masterService.changeEmail(changeMasterDto));

        given(masterRepository.findByUsername(changeMasterDto.getUsername())).willReturn(master = null);
        assertThrows(NotFoundException.class, () -> masterService.changeEmail(changeMasterDto));

    }
}
