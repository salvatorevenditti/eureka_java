package com.it.net.eureka.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

@ExtendWith(MockitoExtension.class)
public class CryptoUtilTest {
	
	@Mock
	private Logger log;
	
	@InjectMocks
	private CryptoUtil cryptoUtil;

	@BeforeEach
	protected void setUp() throws Exception {
	}

	@Test
	public final void testGenerateHashWithGivenSalt() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testGenerateSalt() throws Exception {
		throw new RuntimeException("not yet implemented");
	}
}
