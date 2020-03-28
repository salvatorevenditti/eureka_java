package com.it.net.eureka.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CostantsTest {

	@BeforeEach
	protected void setUp() throws Exception {
	}
	
	@Test
	public void nothingToTest() {
		assertNotNull(Costants.DATE_TEST);
		assertNotNull(Costants.INT_TEST);
		assertNotNull(Costants.OFF_DT_TIME_TEST);
		assertNotNull(Costants.STR_TEST);
	}
}
