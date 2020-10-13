package com.it.net.eureka.dto;

import com.it.net.eureka.utils.Costants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CreateMasterDtoTest {

    @InjectMocks
    private CreateMasterDto createMasterDto;

    @BeforeEach
    protected void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        createMasterDto = new CreateMasterDto();
    }

    @Test
    public final void getterAndSetterTest() {
        createMasterDto.setEnabled(Costants.BOOL_TRUE_TEST);
        assertEquals(Costants.BOOL_TRUE_TEST, createMasterDto.isEnabled());
        createMasterDto.setConfirmPassword(Costants.STR_TEST);
        assertEquals(Costants.STR_TEST, createMasterDto.getConfirmPassword());
    }
}
