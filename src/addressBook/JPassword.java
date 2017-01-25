//�W�١G�ˬd�b���K�X
//�]�p�v�G�N�L��

package addressBook;

import java.util.Properties;
import java.io.*;

import javax.swing.JOptionPane;

public class JPassword extends JPasswordUI
{
	private String id,txtID,txtPW,pw; //���o�b���K�X�r��
	private Properties props; //�Ψ�Ū��txt�̪��b�K��
	
	//�ˬd�b�K
	protected void checkID_PW()
	{
		id = this.getID();
		pw = new String(this.getPW());
		props = new Properties();
		try {
		props.load(new FileInputStream("init.txt")); //Ū���]�w��
        } catch (IOException ioe) {
		JOptionPane.showMessageDialog(null, ioe.toString(),
                    		"�}�� init.txt ���~!", JOptionPane.ERROR_MESSAGE);
        }
		txtID = props.getProperty("bookID");
		txtPW = props.getProperty("bookPW");

		txtID = aes.getdecrypt(key,txtID); //�N�b����aes�ѱK
		txtPW = aes.getdecrypt(key,txtPW); //�N�K�X��aes�ѱK
		
		if(pw.length() > 8)
		{
	    		JOptionPane.showMessageDialog(null,
			"�K�X���׶W�L8�Ӧr��","�T��",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		if(id.equals(txtID) && pw.equals(txtPW) )
		{
			new JAddressBook();
			this.setVisible(false);
			return;
		}
	    	JOptionPane.showMessageDialog(null,
		"�b���K�X���~!!","�T��",JOptionPane.INFORMATION_MESSAGE);
	}
}
