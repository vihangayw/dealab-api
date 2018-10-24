package com.zinios.dealab.utils;

import org.apache.commons.lang3.RandomStringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Random;

public class PasswordUtils {
	private static final String ALGORITHM = "AES";
	private static final byte[] keyValue =
			new byte[]{'V', 'I', 'S', 'U', 'z', 'i', 'n', 'i', 'O', 'S', 'd', 'E', 'A', 'L', 'a', 'b'};

	public static String encrypt(String valueToEnc) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGORITHM);
		c.init(Cipher.ENCRYPT_MODE, key);
		return new BASE64Encoder().encode(c.doFinal(valueToEnc.getBytes()));
	}

	public static String decrypt(String encryptedValue) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGORITHM);
		c.init(Cipher.DECRYPT_MODE, key);
		return new String(c.doFinal(new BASE64Decoder().decodeBuffer(encryptedValue)));
	}

	private static Key generateKey() throws Exception {
		return new SecretKeySpec(keyValue, ALGORITHM);
	}

	public static String getRandomPassword() {
		StringBuilder pw = new StringBuilder();
		int randomValue;
		Random random = new Random();
		randomValue = random.nextInt(10);
		while (pw.length() < 8) {
			pw.append(randomValue);
			randomValue = random.nextInt(10);
			if (randomValue % 2 == 0) {
				pw.append(RandomStringUtils.randomAlphabetic(1));
			}
		}
		try {
			return encrypt(pw.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return (pw.toString());
		}
	}
}
