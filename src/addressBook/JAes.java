//程式名稱：AES加密解密

package addressBook;

import java.io.*;
import sun.misc.*;
import javax.crypto.*;
import java.security.*;
import javax.crypto.spec.*;
import javax.swing.*;

public class JAes
{
	private final static int KEY_SIZE=128;

	//加密
	public String getencrypt(String key, String plain)
	{
		String encrypted="";

		try
		{
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			 //防止Linux下隨機生成Key
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );  
            secureRandom.setSeed(key.getBytes());  
            // 根據金鑰初始化金鑰產生器
            kgen.init(KEY_SIZE, secureRandom); 
			//kgen.init(KEY_SIZE, new SecureRandom(key.getBytes()));
			SecretKey skey = kgen.generateKey();
			SecretKeySpec skeySpec = new SecretKeySpec(skey.getEncoded(), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			byte[] encrypt =cipher.doFinal(plain.getBytes("UTF8"));
			encrypted=new BASE64Encoder().encodeBuffer(encrypt);
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.toString(),
			"aes加密失敗", JOptionPane.ERROR_MESSAGE);
		}
		return encrypted;
	}

	//解密
	public String getdecrypt(String k2, String base64)
	{
		String decrypted="";

		try
		{
			KeyGenerator kgen2 = KeyGenerator.getInstance("AES");
			//防止Linux下隨機生成Key
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );  
            secureRandom.setSeed(k2.getBytes());  
            // 根據金鑰初始化金鑰產生器
            kgen2.init(KEY_SIZE, secureRandom); 
			//kgen2.init(KEY_SIZE, new SecureRandom(k2.getBytes()));
			SecretKey skey2 = kgen2.generateKey();
			SecretKeySpec skeySpec2 = new SecretKeySpec(skey2.getEncoded(), "AES");

			Cipher cipher2 = Cipher.getInstance("AES");
			cipher2.init(Cipher.DECRYPT_MODE, skeySpec2);
			byte[] decrypt =cipher2.doFinal(new BASE64Decoder().decodeBuffer(base64));
			decrypted=new String(decrypt, "UTF8");
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.toString(),
			"aes解密失敗", JOptionPane.ERROR_MESSAGE);
		}
		return decrypted;
	}

}