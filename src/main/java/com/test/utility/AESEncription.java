package com.test.utility;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESEncription {

	private static final String secretKey = "ThisIsASecretKey";

	public static String encrypt(String input) throws Exception {
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			byte[] iv = new byte[16];
			IvParameterSpec ivSpec = new IvParameterSpec(iv);
			SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "AES");

			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivSpec);
			byte[] encryptedBytes = cipher.doFinal(input.getBytes());

			return Base64.getEncoder().withoutPadding().encodeToString(encryptedBytes);
		} catch (Exception ex) {

			return "Not Encripted";

		}
	}

	public static String decrypt(String encryptedInput) throws Exception {
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			byte[] iv = new byte[16];
			IvParameterSpec ivSpec = new IvParameterSpec(iv);
			SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "AES");

			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivSpec);
			byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedInput));

			return new String(decryptedBytes);
		} catch (Exception ex) {

			return "Not Decrypted";

		}
	}
	
//  public static void main(String[] args) throws Exception {
//  String originalText = "P@ssw0rd";
//  String encryptedText = encrypt(originalText);
//  
//  System.out.println("Original Text: " + originalText);
//  System.out.println("Encrypted Text: " + encryptedText);
//  
//  String decryptedText = decrypt(encryptedText);
//  System.out.println("Decrypted Text: " + decryptedText);
//}

}
