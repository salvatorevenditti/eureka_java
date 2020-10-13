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
public class ChangeMasterDtoTest {

    @InjectMocks
    private ChangeMasterDto changeMasterDto;

    @BeforeEach
    protected void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        changeMasterDto = new ChangeMasterDto();
    }

    @Test
    public final void getterAndSetterTest() {
        changeMasterDto.setNewPassword(Costants.STR_TEST);
        assertEquals(Costants.STR_TEST, changeMasterDto.getNewPassword());
    }
}
