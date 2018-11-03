package com.happy.util;

import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * ���ܽ��ܹ��߰�
 */
public class DesEncryptUtil {

	public static final String ALGORITHM_DES = "DES/CBC/PKCS5Padding";

	public static void main(String[] args) {
		String aString = "asdfghjkl123456789";
		DESdecode(aString);

	}

	/**
	 * DES加密
	 * 
	 * @param data
	 *            ������ַ�
	 * @param key
	 *            ����˽Կ�����Ȳ��ܹ�С��8λ
	 * @return ���ܺ���ֽ����飬һ����Base64����ʹ��
	 * @throws InvalidAlgorithmParameterException
	 * @throws Exception
	 */
	//原文：123	密文：333955B0963DA106	//原文：sqsq..	密文：4BD46849EA518CEB	
	//原文：kxdd..	密文：B4A65284EF2FA21F	//原文：ljx	密文：1962BC5A49F0BCF1
	public static String DESencode(String data) {		

		String key = "helloqt2";

		if (data == null)
			return null;
		try {
			DESKeySpec dks = new DESKeySpec(key.getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			// key�ĳ��Ȳ��ܹ�С��8λ�ֽ�
			Key secretKey = keyFactory.generateSecret(dks);
			Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
			IvParameterSpec iv = new IvParameterSpec("qt_media".getBytes());
			AlgorithmParameterSpec paramSpec = iv;
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, paramSpec);
			byte[] bytes = cipher.doFinal(data.getBytes());
			return byte2hex(bytes);
		} catch (Exception e) {
			e.printStackTrace();
			return data;
		}
	}

	/**
	 * DES解密
	 * 
	 * @param data
	 *            ������ַ�
	 * @param key
	 *            ����˽Կ�����Ȳ��ܹ�С��8λ
	 * @return ���ܺ���ֽ�����
	 * @throws Exception
	 *             �쳣
	 */
	public static String DESdecode(String data) {

		String key = "helloqt2";

		if (data == null)
			return null;
		try {
			DESKeySpec dks = new DESKeySpec(key.getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			// key�ĳ��Ȳ��ܹ�С��8λ�ֽ�
			Key secretKey = keyFactory.generateSecret(dks);
			Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
			IvParameterSpec iv = new IvParameterSpec("qt_media".getBytes());
			AlgorithmParameterSpec paramSpec = iv;
			cipher.init(Cipher.DECRYPT_MODE, secretKey, paramSpec);
			return new String(cipher.doFinal(hex2byte(data.getBytes())),"utf-8");
		} catch (Exception e) {
			// e.printStackTrace();
			return "error";
		}
	}

	/**
	 * ������ת�ַ�
	 * 
	 * @param b
	 * @return
	 */
	private static String byte2hex(byte[] b) {
		StringBuilder hs = new StringBuilder();
		String stmp;
		for (int n = 0; b != null && n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0XFF);
			if (stmp.length() == 1)
				hs.append('0');
			hs.append(stmp);
		}
		return hs.toString().toUpperCase();
	}

	private static byte[] hex2byte(byte[] b) {
		if ((b.length % 2) != 0)
			throw new IllegalArgumentException();
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}

}
