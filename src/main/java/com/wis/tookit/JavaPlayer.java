package com.wis.tookit;

import java.net.URLDecoder;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import sun.misc.BASE64Encoder;

import com.wis.tookit.security.Coder;

public class JavaPlayer {

	public static void main(String[] args) throws Exception {
		String mobile = "13953699650";
		String cardno = "370723197210283319";
		String name = URLDecoder.decode("张振忠", "utf-8");
		
		// 密钥  12345678 加密字符串phone =xxxxxxxxxxxx 加密结果  3lsy6mASAwBgmZWL0QQpIIu76EI6b56I
		
		String desStr = DESEncrypt.encode("uOl4txvu", "name=%E5%BC%A0%E6%8C%AF%E5%BF%A0&identityCard=370723197210283319&phone=13953699650");
		
		System.out.println(desStr);
		
		BASE64Encoder encoder = new BASE64Encoder();
		desStr = encoder.encode(desStr.getBytes("UTF-8"));
		System.out.println(desStr);
	}
	
	private void generatorKey() throws Exception {
		KeyGenerator keygen = KeyGenerator.getInstance("AES");
		SecretKey deskey = keygen.generateKey();
		System.out.println(Coder.encryptBASE64(deskey.getEncoded()));
	}
	
}
