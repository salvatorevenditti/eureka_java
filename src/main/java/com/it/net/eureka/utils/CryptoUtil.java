package com.it.net.eureka.utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class CryptoUtil {

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
			e.printStackTrace();
		}
		return hash;
	}
}