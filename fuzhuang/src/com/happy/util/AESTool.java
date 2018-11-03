/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happy.util;

import org.apache.log4j.Logger;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.engines.AESFastEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.encoders.Hex;

/**
 *
 * @author Administrator
 */
public class AESTool {
	protected static final Logger log = Logger.getLogger(AESTool.class);   
	private byte[] initVector = { 0x32, 0x37, 0x36, 0x35, 0x34, 0x33, 0x32, 0x31, 0x38, 0x27, 0x36, 0x35, 0x33, 0x23, 0x32, 0x31 };  
	/** 
	 * FIXME For demo only, should rewrite this method in your product environment! 
	 *  秘钥
	 * @param appid 
	 * @return 
	 */  
	public String findKeyById(String appid) {   
		String key = "123456789012345678901234567890~!";  
		return key;
	}  
	/**
	 * 加密
	 * @param content 加密内容
	 * @param key 加密秘钥
	 * @return
	 * @throws Exception
	 */
	public String encrypt(String content, String key) throws Exception {  
		if (key == null) {  
			throw new IllegalArgumentException("Key cannot be null!");  
		}  
		String encrypted = null;  
		byte[] keyBytes = key.getBytes();  
		if (keyBytes.length != 32 && keyBytes.length != 24  && keyBytes.length != 16) {  
			throw new IllegalArgumentException("Key length must be 128/192/256 bits!");  
		}  
		byte[] encryptedBytes = null;  
		encryptedBytes = encrypt(content.getBytes(), keyBytes, initVector);  
		encrypted = new String(Hex.encode(encryptedBytes));  
		return encrypted;  
	}
	/** 
	 * 解密
	 * Decrypt the content with a given key using aes algorithm. 
	 *  
	 * @param content 解密内容
	 * @param key 解密秘钥
	 *          must contain exactly 32 characters 
	 * @return 
	 * @throws Exception  
	 */  
	public String decrypt(String content, String key) throws Exception {  
		if (key == null) {  
			throw new IllegalArgumentException("Key cannot be null!");  
		}  
		String decrypted = null;  
		byte[] encryptedContent = Hex.decode(content);  
		byte[] keyBytes = key.getBytes();  
		byte[] decryptedBytes = null;  
		if (keyBytes.length != 32 && keyBytes.length != 24  
				&& keyBytes.length != 16) {  
			throw new IllegalArgumentException(  
					"Key length must be 128/192/256 bits!");  
		}          
		decryptedBytes = decrypt(encryptedContent, keyBytes, initVector);  
		decrypted = new String(decryptedBytes);
		return decrypted;  
	}  

	/** 
	 * Encrypt data. 
	 *  
	 * @param plain 
	 * @param key 
	 * @param iv 
	 * @return 
	 * @throws Exception 
	 */  
	public byte[] encrypt(byte[] plain, byte[] key, byte[] iv) throws Exception {  
		PaddedBufferedBlockCipher aes = new PaddedBufferedBlockCipher(  
				new CBCBlockCipher(new AESFastEngine()));  
		CipherParameters ivAndKey = new ParametersWithIV(new KeyParameter(key),iv);  
		aes.init(true, ivAndKey);  
		return cipherData(aes, plain);  
	}  
	/** 
	 * Decrypt data. 
	 *  
	 * @param cipher 
	 * @param key 
	 * @param iv 
	 * @return 
	 * @throws Exception 
	 */  
	public byte[] decrypt(byte[] cipher, byte[] key, byte[] iv)  
			throws Exception {  
		PaddedBufferedBlockCipher aes = new PaddedBufferedBlockCipher(  
				new CBCBlockCipher(new AESFastEngine()));  
		CipherParameters ivAndKey = new ParametersWithIV(new KeyParameter(key),  
				iv);  
		aes.init(false, ivAndKey);  
		return cipherData(aes, cipher);  
	}  

	/** 
	 * Encrypt or decrypt data. 
	 *  
	 * @param cipher 
	 * @param data 
	 * @return 
	 * @throws Exception 
	 */  
	private byte[] cipherData(PaddedBufferedBlockCipher cipher, byte[] data)  
			throws Exception {  
		int minSize = cipher.getOutputSize(data.length);  
		byte[] outBuf = new byte[minSize];  
		int length1 = cipher.processBytes(data, 0, data.length, outBuf, 0);  
		int length2 = cipher.doFinal(outBuf, length1);  
		int actualLength = length1 + length2;  
		byte[] result = new byte[actualLength];
		System.arraycopy(outBuf, 0, result, 0, result.length);  
		return result;
	}
	public static void main(String[] args) throws Exception{
		AESTool aesTool=new AESTool();
		String appid="qtmedia";
		//String key=aesTool.findKeyById(appid);//获取秘钥
		//String key=ApplicationUtil.getDecryptkey();//获取秘钥		
		//String key="conf.global.decrypt.key";
		//key=PropertiesUtils.getValue(key);
		//System.out.println("key:"+key);
//		String json="{\"ENAME\":\"哈哈哈\",\"isParent\":true,\"ENO\":1,\"name\":\"研发部\",\"pId\":\"1\",\"id\":3}";
//		//System.out.println("json:"+json);
		
		String jsonobject = "{\"user_id\":\"135\",\"accountnum\":\"13767151408\",\"accountpassword\":\"666666\",\"withdraw_money\":\"10.00\"}";
		//String jsonkey = "qtmedia";		//明文： qtmedia；密文：f11078bd5af303224a58f2ad0f6fda35
		//String jsonkey = "123";		//明文： 123；密文：9018cdfed48ceb86c6172c694bb35321
		String jsonkey = "20160715qt#*";		//明文：20160715qt#*；密文：55449c912d5f99662964c807a03c6457
		//String jsonkey = "666666";		//明文：666666；密文：3de0f2b83bdc83761c83ea0d51f2dbb3
		//String encrypted = aesTool.encrypt(jsonkey, key); //加密
		//System.out.println("encrypted:"+encrypted);
		
		//String decrypted = aesTool.decrypt(encrypted, key);  //解密
		//System.out.println("decrypted:"+decrypted);
		
		//boolean isSuccessful = StringUtils.equals(decrypted, jsonobject);
		//System.out.println("isSuccessful:"+isSuccessful);
/*		Date date = new Date();
		date=new Date(date.getTime() - 24*60*60*1000);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currenttime = formatter.format(date);		
		System.out.println(date);
		System.out.println(currenttime);
*/	}
}
