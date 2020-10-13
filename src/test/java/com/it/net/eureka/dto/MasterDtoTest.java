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
public class MasterDtoTest {

    @InjectMocks
    private MasterDto masterDto;

    @BeforeEach
    protected void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        masterDto = new MasterDto();
    }

    @Test
    public final void getterAndSetterTest() {
        masterDto.setEmail(Costants.STR_TEST);
        assertEquals(Costants.STR_TEST, masterDto.getEmail());
        masterDto.setUsername(Costants.STR_TEST);
        assertEquals(Costants.STR_TEST, masterDto.getUsername());
    }
}
