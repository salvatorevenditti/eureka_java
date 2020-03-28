package com.it.net.eureka.dto.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.it.net.eureka.utils.Costants;

@ExtendWith(MockitoExtension.class)
public class LoginUserDtoTest {
	
	@InjectMocks
	private LoginUserDto loginUserDto;

	@BeforeEach
	protected void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		loginUserDto = new LoginUserDto();
	}
	
	@Test
	public final void getterAndSetterTest() {
		loginUserDto.setPassword(Costants.STR_TEST);
		assertEquals(Costants.STR_TEST, loginUserDto.getPassword());
		loginUserDto.setInputField(Costants.STR_TEST);
		assertEquals(Costants.STR_TEST, loginUserDto.getInputField());
	}
}
