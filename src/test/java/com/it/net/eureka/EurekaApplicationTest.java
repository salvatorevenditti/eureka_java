package com.it.net.eureka;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EurekaApplicationTest {
//TODO Complete test

	@BeforeEach
	protected void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public final void testProductAPI() throws Exception {
		assertNotNull(EurekaApplication.productApi());
	}

	@Test
	public final void testDiscovers() throws Exception {
		assertNotNull(EurekaApplication.discovers());
	}

	@Test
	public final void testSimpleEmail() throws Exception {
		assertNotNull(EurekaApplication.emailTemplate());
	}
}
