package com.it.example.demo.utils;

import java.security.SecureRandom;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import lombok.Data;

@Data
public class CryptoUtil {

	public static byte[] generateHashWithGivenSalt(String pwd, byte[] salt) {

		KeySpec spec = new PBEKeySpec(pwd.toCharArray(), salt, 65536, 128);
		byte[] hash = null;
		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			hash = factory.generateSecret(spec).getEncoded();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return hash;
	}

	public static byte[] generateSalt() {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16];
		random.nextBytes(salt);

		return salt;
	}
}