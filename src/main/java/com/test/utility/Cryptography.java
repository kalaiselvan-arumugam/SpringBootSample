package com.test.utility;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class Cryptography {

	private static final String KEY = "$2a$12$FmWetHyQV1w2Hasb09hn7eBYeX/c0.4eDLemJ8zPttvAIlPsMDCoW";


	private String encrypt(String unencryptedString) {
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			byte[] digestOfPassword = md.digest(KEY.getBytes("utf-8"));
			byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
			for (int j = 0, k = 16; j < 8;) {
				keyBytes[k++] = keyBytes[j++];
			}
			SecretKey secretKey = new SecretKeySpec(keyBytes, "DESede");
			Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			byte[] plainTextBytes = unencryptedString.getBytes("utf-8");
			byte[] buf = cipher.doFinal(plainTextBytes);
			byte[] base64Bytes = Base64.encodeBase64(buf);
			String base64EncryptedString = new String(base64Bytes);
			return base64EncryptedString;
		} catch (Exception ex) {
			return null;
		}
	}

	private  String decrypt(String encryptedString) {
		try {
			if (encryptedString == null) {
				return null;
			}
			byte[] message = Base64.decodeBase64(encryptedString.getBytes(StandardCharsets.UTF_8));
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digestOfPassword = md.digest(KEY.getBytes(StandardCharsets.UTF_8));
			byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
			for (int j = 0, k = 16; j < 8;) {
				keyBytes[k++] = keyBytes[j++];
			}
			SecretKey secretKey = new SecretKeySpec(keyBytes, "DESede");
			Cipher decipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
			decipher.init(Cipher.DECRYPT_MODE, secretKey);
			return new String(decipher.doFinal(message), StandardCharsets.UTF_8);
		} catch (Exception ex) {
			return null;
		}
	}

	public  static String getChiper(String plainText) {
		Cryptography cryptography = new Cryptography();
		return cryptography.encrypt(plainText);
	}

	public static  String getPlainText(String chiper) {
		Cryptography cryptography = new Cryptography();
		return cryptography.decrypt(chiper);
	}


}
