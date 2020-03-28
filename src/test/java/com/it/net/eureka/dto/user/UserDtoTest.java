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
public class UserDtoTest {
	
	@InjectMocks
	private UserDto userDto;

	@BeforeEach
	protected void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		userDto = new UserDto();
	}
	
	@Test
	public final void getterAndSetterTest() {
		userDto.setEmail(Costants.STR_TEST);
		assertEquals(Costants.STR_TEST, userDto.getEmail());
		userDto.setUsername(Costants.STR_TEST);
		assertEquals(Costants.STR_TEST, userDto.getUsername());
	}
}
