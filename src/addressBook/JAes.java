//�{���W�١GAES�[�K�ѱK

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

	//�[�K
	public String getencrypt(String key, String plain)
	{
		String encrypted="";

		try
		{
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(KEY_SIZE, new SecureRandom(key.getBytes()));
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
			"aes�[�K����", JOptionPane.ERROR_MESSAGE);
		}
		return encrypted;
	}

	//�ѱK
	public String getdecrypt(String k2, String base64)
	{
		String decrypted="";

		try
		{
			KeyGenerator kgen2 = KeyGenerator.getInstance("AES");
			kgen2.init(KEY_SIZE, new SecureRandom(k2.getBytes()));
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
			"aes�ѱK����", JOptionPane.ERROR_MESSAGE);
		}
		return decrypted;
	}

}