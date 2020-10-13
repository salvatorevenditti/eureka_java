package com.it.net.eureka.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.it.net.eureka.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.it.net.eureka.dto.CreateUserDto;
import com.it.net.eureka.utils.Costants;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class UserTest {

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
		assertNotNull(user.getInsertDate());
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
		user.setUserEmail(Costants.STR_TEST);
		assertEquals(Costants.STR_TEST, user.getUserEmail());
		user.setUserHashPassword(Costants.BYTE_TEST);
		assertEquals(Costants.BYTE_TEST, user.getUserHashPassword());
		user.setLastChangeDate(Costants.OFF_DT_STR_TIME_TEST);
		assertEquals(Costants.OFF_DT_STR_TIME_TEST, user.getLastChangeDate());
		user.setInsertDate(Costants.OFF_DT_STR_TIME_TEST);
		assertEquals(Costants.OFF_DT_STR_TIME_TEST, user.getInsertDate());
		user.setUserSaltPassword(Costants.BYTE_TEST);
		assertEquals(Costants.BYTE_TEST, user.getUserSaltPassword());
		user.setUserId(Costants.INT_TEST);
		assertEquals(Costants.INT_TEST, user.getUserId());
		user.setUserUsername(Costants.STR_TEST);
		assertEquals(Costants.STR_TEST, user.getUserUsername());
		user.setCorrelationId(Costants.CORRELATION_UUID);
		assertEquals(Costants.CORRELATION_UUID, user.getCorrelationId());
	}
}
