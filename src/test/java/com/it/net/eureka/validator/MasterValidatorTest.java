package com.it.net.eureka.validator;

import com.it.net.eureka.model.Master;
import com.it.net.eureka.repo.MasterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ValidationException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MasterValidatorTest {

    private static final String STR_PWD_TRUE = "TEST89.ita";
    private static final String STR_PWD_FALSE = "TEST";
    private static final String STR_USERNAME = "TEST";
    private static final String STR_EMAIL = "TESTEST@GMAIL.COM";

    @Mock
    private MasterRepository masterRepository;

    @InjectMocks
    private MasterValidator masterValidator;

    @Mock
    private Master master;

    @BeforeEach
    protected void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test()
    public final void testCheckIfAlreadyExistsStringStringTrue() throws Exception {
        when(masterRepository.findIfExistsByUsername(STR_USERNAME)).thenReturn(true);
        when(masterRepository.findIfExistsByEmail(STR_EMAIL)).thenReturn(true);
        assertThrows(ValidationException.class, () -> masterValidator.checkIfAlreadyExists(STR_USERNAME, STR_EMAIL));
        when(masterRepository.findIfExistsByUsername(STR_USERNAME)).thenReturn(false);
        assertThrows(ValidationException.class, () -> masterValidator.checkIfAlreadyExists(STR_USERNAME, STR_EMAIL));
    }

    @Test
    public final void testCheckIfAlreadyExistsStringStringFalse() throws Exception {
        when(masterRepository.findIfExistsByUsername(STR_USERNAME)).thenReturn(false);
        when(masterRepository.findIfExistsByEmail(STR_EMAIL)).thenReturn(false);
        assertDoesNotThrow( () -> masterValidator.checkIfAlreadyExists(STR_USERNAME, STR_EMAIL));
    }

    @Test
    public final void testCheckIfAlreadyExistsStringTrue() throws Exception {
        given(masterValidator.checkIfAlreadyExists(STR_EMAIL)).willReturn(master);
        assertNotNull(masterValidator.checkIfAlreadyExists(STR_EMAIL));
        given(masterValidator.checkIfAlreadyExists(STR_USERNAME)).willReturn(master);
        assertNotNull(masterValidator.checkIfAlreadyExists(STR_USERNAME));
    }

    @Test
    public final void testCheckPswTrue() throws Exception {
        assertEquals(true, masterValidator.checkPsw(STR_PWD_TRUE));
    }

    @Test
    public final void testCheckPswFalse() throws Exception {
        assertEquals(false, masterValidator.checkPsw(STR_PWD_FALSE));
    }

}
