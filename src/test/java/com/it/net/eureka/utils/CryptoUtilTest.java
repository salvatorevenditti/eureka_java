package com.it.net.eureka.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CryptoUtilTest {

	@BeforeEach
	protected void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public final void testGenerateHashWithGivenSaltNoException() {
		assertNotNull(CryptoUtil.generateHashWithGivenSalt(Costants.STR_TEST, CryptoUtil.generateSalt()));
	}

	@Test
	public final void testPrivateConstructor() throws NoSuchMethodException {
		Constructor<CryptoUtil> constructor = CryptoUtil.class.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(constructor.getModifiers()));
		constructor.setAccessible(true);
		assertThrows(InvocationTargetException.class, () -> constructor.newInstance());
	}
}	