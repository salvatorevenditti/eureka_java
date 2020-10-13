package com.it.net.eureka.controller;

import com.it.net.eureka.dto.ChangeMasterDto;
import com.it.net.eureka.dto.CreateMasterDto;
import com.it.net.eureka.dto.LoginMasterDto;
import com.it.net.eureka.model.Master;
import com.it.net.eureka.service.EmailService;
import com.it.net.eureka.service.MasterService;
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
public class MasterAccessControllerTest {

    @InjectMocks
    private MasterAccessController masterAccessController;

    @Mock
    private MasterService masterService;

    @Mock
    private Master master;

    @Mock
    private EmailService emailService;

    private CreateMasterDto createMasterDto;
    private ChangeMasterDto changeMasterDto;
    private LoginMasterDto loginMasterDto;

    @BeforeEach
    protected void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public final void testSignUp() throws Exception {
        given(masterService.createMaster(createMasterDto)).willReturn(master);
        assertNotNull(masterAccessController.signUp(createMasterDto));
    }

    @Test
    public final void testLogIn() throws Exception {
        given(masterService.loginMaster(loginMasterDto)).willReturn(master);
        assertNotNull(masterAccessController.logIn(loginMasterDto));
    }

    @Test
    public final void testChangePassword() throws Exception {
        given(masterService.changePassword(changeMasterDto)).willReturn(master);
        assertNotNull(masterAccessController.changePassword(changeMasterDto));
    }

    @Test
    public final void testChangeEmail() throws Exception {
        given(masterService.changeEmail(changeMasterDto)).willReturn(master);
        assertNotNull(masterAccessController.changeEmail(changeMasterDto));
    }

    @Test
    public final void testChangeUsername() throws Exception {
        given(masterService.changeUsername(changeMasterDto)).willReturn(master);
        assertNotNull(masterAccessController.changeUsername(changeMasterDto));
    }
}
