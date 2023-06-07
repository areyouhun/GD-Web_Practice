package com.web.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class AESEncryptor {
	
	private static SecretKey key;
	private String path;
	
	public AESEncryptor() {
		this.path = AESEncryptor.class.getResource("/").getPath();
		
//		WEB-INF 폴더에 저장할 경우
//		this.path = this.path.substring(0, this.path.indexOf("classes"));
		
		File keyFile = new File(this.path + "bslove.bs");
		
		if (keyFile.exists()) {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(keyFile))) {
				AESEncryptor.key = (SecretKey) ois.readObject();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			generateKeyBy(keyFile);
		}
	}

	private void generateKeyBy(File keyFile) {
		SecureRandom secRnd = new SecureRandom();
		KeyGenerator keyGen = null;
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(keyFile))) {
			keyGen = KeyGenerator.getInstance("AES");
			keyGen.init(128, secRnd);
			AESEncryptor.key = keyGen.generateKey();
			
			oos.writeObject(AESEncryptor.key);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public static String encrypt(String dataOriginal) throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, AESEncryptor.key);
		
		byte[] bytesOriginal = dataOriginal.getBytes(Charset.forName("UTF-8"));
		byte[] bytesEncrypted = cipher.doFinal(bytesOriginal);
		return Base64.getEncoder().encodeToString(bytesEncrypted);
	}
	
	public static String decrypt(String dataEncrypted) throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, AESEncryptor.key);
		
		byte[] bytesToBeDecrypted = Base64.getDecoder().decode(dataEncrypted.getBytes(Charset.forName("UTF-8")));
		byte[] bytesDecrypted = cipher.doFinal(bytesToBeDecrypted);
		return new String(bytesDecrypted);
	}
}
