//名稱：檢查帳號密碼
//設計師：吉他手

package addressBook;

import java.util.Properties;
import java.io.*;

import javax.swing.JOptionPane;

public class JPassword extends JPasswordUI
{
	private String id,txtID,txtPW,pw; //取得帳號密碼字串
	private Properties props; //用來讀取txt裡的帳密值
	
	//檢查帳密
	protected void checkID_PW()
	{
		id = this.getID();
		pw = new String(this.getPW());
		props = new Properties();
		try {
		props.load(new FileInputStream("src/init.txt")); //讀取設定檔
        } catch (IOException ioe) {
		JOptionPane.showMessageDialog(null, ioe.toString(),
                    		"開啟 init.txt 錯誤!", JOptionPane.ERROR_MESSAGE);
        }
		txtID = props.getProperty("bookID");
		txtPW = props.getProperty("bookPW");

		txtID = aes.getdecrypt(key,txtID); //將帳號用aes解密
		txtPW = aes.getdecrypt(key,txtPW); //將密碼用aes解密
		
		if(pw.length() > 8)
		{
	    		JOptionPane.showMessageDialog(null,
			"密碼長度超過8個字元","訊息",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		if(id.equals(txtID) && pw.equals(txtPW) )
		{
			new JAddressBook();
			this.setVisible(false);
			return;
		}
	    	JOptionPane.showMessageDialog(null,
		"帳號密碼錯誤!!","訊息",JOptionPane.INFORMATION_MESSAGE);
	}
}