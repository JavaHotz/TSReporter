//名稱：通訊錄設定新帳號與密碼介面
//設計師：吉他手

package addressBook;

import javax.swing.*;
import java.util.Properties;
import java.io.*;
import java.awt.event.*;

public class JSetting extends JPasswordUI
{
	private String id,pw; //取得帳號密碼字串
	private Properties props; //用來讀取txt裡的帳密值
	
	public JSetting()
	{
		this.setTitle("修改帳密");
		Jlb_ID.setText("新帳號");
		Jlb_ID.setSize(60,20);
		Jlb_ID.setLocation(10,10);
		Jlb_PW.setText("新密碼");
		Jlb_PW.setSize(60,20);
		Jlb_PW.setLocation(10,50);

		props = new Properties();
		
		//設定視窗
		setSize(200,150);
		setLocation(300,200); 
 		setResizable(false);//視窗放大按鈕無效 
 		setVisible(true);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
 		//按下視窗關閉鈕事件處理
                addWindowListener(
                    new WindowAdapter() {
                        public void windowClosing(WindowEvent e) { 
                        	setVisible(false);
                        }
                    }
                ); 
	} 
	protected void checkID_PW()
	{
		if(!checkIsLetterOrDigit(this.getID().toCharArray())) return;
		if(!checkIsLetterOrDigit(this.getPW())) return;

		id = this.getID();

		pw = new String(this.getPW());
		
		if(pw.length() > 8)
		{
	    		JOptionPane.showMessageDialog(null,
	    			"密碼長度超過8個字元","訊息",JOptionPane.INFORMATION_MESSAGE);
	    		return;
		}
		
		if(id.isEmpty() || pw.isEmpty())
		{
	    		JOptionPane.showMessageDialog(null,
	    			"帳號或密碼不得空白","訊息",JOptionPane.INFORMATION_MESSAGE);
	    		return;
		}

    		JOptionPane.showMessageDialog(null,
    		"新帳號 : " + id + "\n新密碼 : " + pw + "\n請牢記!",
		"新帳號密碼設定完成",JOptionPane.INFORMATION_MESSAGE);
		id = aes.getencrypt(key,id); //將帳號用aes加密
		pw = aes.getencrypt(key,pw); //將密碼用aes加密
		props.setProperty("bookID", id); //設定新帳號
		props.setProperty("bookPW", pw); //設定新密碼
		try {
			props.store(new FileWriter("init.txt"), "bookID");//寫入新帳號
			props.store(new FileWriter("init.txt"), "bookPW");//寫入新密碼
		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(null, ioe.toString(),
                    	"開啟 init.txt 錯誤!", JOptionPane.ERROR_MESSAGE);
        	}

		cleanID_PW();
		setVisible(false);
	}

	//設定是否顯示修改帳密視窗
	public void setJSetShow(boolean b)
	{
		setVisible(b);
	}

	//檢查字元是否為數字或字母、空白
	private boolean checkIsLetterOrDigit(char [] ld)
	{
		for(int i = 0; i < ld.length; i++)
		{
			if(!Character.isLetterOrDigit(ld[i]))
			{
				JOptionPane.showMessageDialog(null,
				"帳號密碼必須為字母或數字!!","訊息",JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
			if(Character.isSpaceChar(ld[i]))
			{
				JOptionPane.showMessageDialog(null,
				"帳號密碼不能含有空白字元!!","訊息",JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args)
	{
		new JSetting();
	}
}
