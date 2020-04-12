package com.it.net.eureka;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

public class ProvaApplicationTest {

	@BeforeEach
	protected void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public final void testMain() throws Exception {
		ProvaApplication.main(new String[]{});
	}

}
