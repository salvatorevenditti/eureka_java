package com.it.net.eureka;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProvaApplicationTest {
//TODO Complete test

	@BeforeEach
	protected void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	/*
	@Test
	public final void testMain() throws Exception {
		ProvaApplication.main(new String[]{});
	}
	 */

	@Test
	public final void testProductAPI() throws Exception {
		assertNotNull(ProvaApplication.productApi());
	}

	@Test
	public final void testDiscovers() throws Exception {
		assertNotNull(ProvaApplication.discovers());
	}
}
