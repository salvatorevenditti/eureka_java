package com.it.net.eureka.model.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.it.net.eureka.dto.user.CreateUserDto;
import com.it.net.eureka.utils.Costants;

@ExtendWith(MockitoExtension.class)
public class UserTest {
	
	protected static final byte[] BYTE_TEST = Costants.STR_TEST.getBytes();

	@InjectMocks
	private User user;

	@Mock
	private CreateUserDto createUserDto;
	
	@BeforeEach
	protected void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		user = new User();
		createUserDto = new CreateUserDto();
		createUserDto.setPassword(Costants.STR_TEST);
	}

	@Test
	public final void testUser() throws Exception {
		assertNotNull(user.getRegistrationDate());
	}

	@Test
	public final void testInit() throws Exception {
		user.init();
		assertNotNull(user.getLastChangeDate());
	}

	@Test
	public final void testMapEntity() throws Exception {
		user.mapEntity(createUserDto);
		assertNotNull(user);
	}

	@Test
	public final void testGetterAndSetter() throws Exception {
		user.setBirthDate(Costants.DATE_TEST);
		assertEquals(Costants.DATE_TEST, user.getBirthDate());
		user.setEmail(Costants.STR_TEST);
		assertEquals(Costants.STR_TEST, user.getEmail());
		user.setHashPassword(BYTE_TEST);
		assertEquals(BYTE_TEST, user.getHashPassword());
		user.setLastChangeDate(Costants.OFF_DT_TIME_TEST);
		assertEquals(Costants.OFF_DT_TIME_TEST, user.getLastChangeDate());
		user.setRegistrationDate(Costants.OFF_DT_TIME_TEST);
		assertEquals(Costants.OFF_DT_TIME_TEST, user.getRegistrationDate());
		user.setSaltPassword(BYTE_TEST);
		assertEquals(BYTE_TEST, user.getSaltPassword());
		user.setUserID(Costants.INT_TEST);
		assertEquals(Costants.INT_TEST, user.getUserID());
		user.setUsername(Costants.STR_TEST);
		assertEquals(Costants.STR_TEST, user.getUsername());
	}
}
