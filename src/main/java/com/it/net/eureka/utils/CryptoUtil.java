package com.it.net.eureka.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class CryptoUtil {

	private static Logger log = LoggerFactory.getLogger(CryptoUtil.class);

	private CryptoUtil() {
		throw new IllegalStateException();
	}

	public static byte[] generateSalt() {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		return salt;
	}

	public static byte[] generateHashWithGivenSalt(String pwd, byte[] salt) {
		KeySpec spec = new PBEKeySpec(pwd.toCharArray(), salt, 65536, 128);
		byte[] hash = null;
		SecretKeyFactory factory;
		try {
			factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			hash = factory.generateSecret(spec).getEncoded();
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			log.error("An error occured!", e);
		}
		return hash;
	}
}