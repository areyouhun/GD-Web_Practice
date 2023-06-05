package com.web.common.filter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class PasswordEncryptionWrapper extends HttpServletRequestWrapper {

	public PasswordEncryptionWrapper(HttpServletRequest request) {
		super(request);
	}
	
	@Override
	public String getParameter(String name) {
		final String dataBinded = super.getParameter(name);
		if (name.equals("password")) {
			return getSHA512(dataBinded);
		}
		return dataBinded;
	}
	
	private String getSHA512(String pwOriginal) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		byte[] pwOriginalToBytes = pwOriginal.getBytes();
		md.update(pwOriginalToBytes);
		byte[] bytesEncrypted = md.digest();
		return Base64.getEncoder().encodeToString(bytesEncrypted);
	}
}
