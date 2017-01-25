//�W�١G�q�T���]�w�s�b���P�K�X����
//�]�p�v�G�N�L��

package addressBook;

import javax.swing.*;
import java.util.Properties;
import java.io.*;
import java.awt.event.*;

public class JSetting extends JPasswordUI
{
	private String id,pw; //���o�b���K�X�r��
	private Properties props; //�Ψ�Ū��txt�̪��b�K��
	
	public JSetting()
	{
		this.setTitle("�ק�b�K");
		Jlb_ID.setText("�s�b��");
		Jlb_ID.setSize(60,20);
		Jlb_ID.setLocation(10,10);
		Jlb_PW.setText("�s�K�X");
		Jlb_PW.setSize(60,20);
		Jlb_PW.setLocation(10,50);

		props = new Properties();
		
		//�]�w����
		setSize(200,150);
		setLocation(300,200); 
 		setResizable(false);//������j���s�L�� 
 		setVisible(true);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
 		//���U���������s�ƥ�B�z
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
	    			"�K�X���׶W�L8�Ӧr��","�T��",JOptionPane.INFORMATION_MESSAGE);
	    		return;
		}
		
		if(id.isEmpty() || pw.isEmpty())
		{
	    		JOptionPane.showMessageDialog(null,
	    			"�b���αK�X���o�ť�","�T��",JOptionPane.INFORMATION_MESSAGE);
	    		return;
		}

    		JOptionPane.showMessageDialog(null,
    		"�s�b�� : " + id + "\n�s�K�X : " + pw + "\n�Шc�O!",
		"�s�b���K�X�]�w����",JOptionPane.INFORMATION_MESSAGE);
		id = aes.getencrypt(key,id); //�N�b����aes�[�K
		pw = aes.getencrypt(key,pw); //�N�K�X��aes�[�K
		props.setProperty("bookID", id); //�]�w�s�b��
		props.setProperty("bookPW", pw); //�]�w�s�K�X
		try {
			props.store(new FileWriter("init.txt"), "bookID");//�g�J�s�b��
			props.store(new FileWriter("init.txt"), "bookPW");//�g�J�s�K�X
		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(null, ioe.toString(),
                    	"�}�� init.txt ���~!", JOptionPane.ERROR_MESSAGE);
        	}

		cleanID_PW();
		setVisible(false);
	}

	//�]�w�O�_��ܭק�b�K����
	public void setJSetShow(boolean b)
	{
		setVisible(b);
	}

	//�ˬd�r���O�_���Ʀr�Φr���B�ť�
	private boolean checkIsLetterOrDigit(char [] ld)
	{
		for(int i = 0; i < ld.length; i++)
		{
			if(!Character.isLetterOrDigit(ld[i]))
			{
				JOptionPane.showMessageDialog(null,
				"�b���K�X�������r���μƦr!!","�T��",JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
			if(Character.isSpaceChar(ld[i]))
			{
				JOptionPane.showMessageDialog(null,
				"�b���K�X����t���ťզr��!!","�T��",JOptionPane.INFORMATION_MESSAGE);
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
