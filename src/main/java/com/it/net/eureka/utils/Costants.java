package com.it.net.eureka.utils;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

public class Costants {

	public static final boolean BOOL_TRUE_TEST = true;
	public static final boolean BOOL_FALSE_TEST = false;
	public static final String STR_TEST = "T";
	public static final Integer INT_TEST = 0;
	public static final String OFF_DT_STR_TIME_TEST = OffsetDateTime.now().toString();
	public static final OffsetDateTime OFF_DT_TIME_TEST = OffsetDateTime.MAX;
	public static final LocalDate DATE_TEST = LocalDate.MAX;
	public static final byte[] BYTE_TEST = Costants.STR_TEST.getBytes();
	public static final String CORRELATION_UUID = UUID.randomUUID().toString();


	private Costants() {}
}
