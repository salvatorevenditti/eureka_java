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
public class CreateUserDtoTest {
	
	@InjectMocks
	private CreateUserDto createUserDto;

	@BeforeEach
	protected void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		createUserDto = new CreateUserDto();
	}
	
	@Test
	public final void getterAndSetterTest() {
		createUserDto.setConfirmPassword(Costants.STR_TEST);
		assertEquals(Costants.STR_TEST, createUserDto.getConfirmPassword());
		createUserDto.setBirthDate(Costants.DATE_TEST);
		assertEquals(Costants.DATE_TEST, createUserDto.getBirthDate());
		createUserDto.setLastChangeDate(Costants.OFF_DT_TIME_TEST);
		assertEquals(Costants.OFF_DT_TIME_TEST, createUserDto.getLastChangeDate());
		createUserDto.setRegistrationDate(Costants.OFF_DT_TIME_TEST);
		assertEquals(Costants.OFF_DT_TIME_TEST, createUserDto.getRegistrationDate());
	}
}
