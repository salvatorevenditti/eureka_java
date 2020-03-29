package com.it.net.eureka.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class CryptoUtil {

	public static byte[] generateHashWithGivenSalt(String pwd, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
		KeySpec spec = new PBEKeySpec(pwd.toCharArray(), salt, 65536, 128);
		byte[] hash = null;
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		hash = factory.generateSecret(spec).getEncoded();
		return hash;
	}

	public static byte[] generateSalt() {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16];
		random.nextBytes(salt);

		return salt;
	}

	private CryptoUtil () {
		throw new IllegalStateException("Utility class");
	}
}