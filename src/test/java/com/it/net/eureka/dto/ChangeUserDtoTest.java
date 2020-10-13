package com.it.net.eureka.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.it.net.eureka.utils.Costants;

@ExtendWith(MockitoExtension.class)
public class ChangeUserDtoTest {
	
	@InjectMocks
	private ChangeUserDto changeUserDto;

	@BeforeEach
	protected void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		changeUserDto = new ChangeUserDto();
	}
	
	@Test
	public final void getterAndSetterTest() {
		changeUserDto.setNewPassword(Costants.STR_TEST);
		assertEquals(Costants.STR_TEST, changeUserDto.getNewPassword());
	}
}
