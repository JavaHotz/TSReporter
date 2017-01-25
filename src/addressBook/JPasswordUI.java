//�W�١G�q�T���K�X��J����
//�]�p�v�G�N�L��

package addressBook;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public abstract class JPasswordUI extends JFrame
{
	protected JLabel Jlb_ID = new JLabel("�b��");
	protected JLabel Jlb_PW = new JLabel("�K�X");
	protected String key = "1535879468"; //�[�ѱK��key
	protected JAes aes = new JAes(); //aes�[�ѱK
	private JPasswordField jpw = new JPasswordField(8);
	private JTextField jid = new JTextField();
	private JButton Jbtn_YES = new JButton("�T�w");
	private JButton Jbtn_NO = new JButton("�M��");
	private ButtonHandler hbtHandler = new ButtonHandler();  //�B�z���s�ƥ� 

	public JPasswordUI()
	{
		super("��J�b���K�X"); 
		Container c = getContentPane(); 
		c.setLayout(null);
		
		//�]�wJlb_ID�j�p��m����ܦr��
		Jlb_ID.setLocation(20,10);
		Jlb_ID.setSize(50,20);
		Jlb_ID.setFont(new Font("Serif",Font.BOLD,16));
		c.add(Jlb_ID);
		
		//�]�w�b����J�ؤj�p��m����ܦr��
		jid.setLocation(70,10);
		jid.setSize(100,20);
		c.add(jid);
		
		//�]�wJlb_PW�j�p��m����ܦr��
		Jlb_PW.setLocation(20,50);
		Jlb_PW.setSize(50,20);
		Jlb_PW.setFont(new Font("Serif",Font.BOLD,16));
		c.add(Jlb_PW);
		
		//�]�w�K�X��J�ؤj�p��m����ܦr��
		jpw.setLocation(70,50);
		jpw.setSize(100,20);
		jpw.setEchoChar('��');
		jpw.setToolTipText("�K�X����8�Ӧr��");
		c.add(jpw);
		
		jpw.addActionListener(hbtHandler);
				
		//�]�w�T�w���s�j�p��m����ܦr��
		Jbtn_YES.setLocation(10,90);
		Jbtn_YES.setSize(80,20);
		Jbtn_YES.addActionListener(hbtHandler);
		c.add(Jbtn_YES);
		
		//�]�w�M�����s�j�p��m����ܦr��
		Jbtn_NO.setLocation(100,90);
		Jbtn_NO.setSize(80,20);
		Jbtn_NO.addActionListener(hbtHandler);
		c.add(Jbtn_NO);
		
		//�]�w����
		setSize(200,150);
		setLocation(300,200); 
 		setResizable(false);//������j���s�L�� 
 		setVisible(true);
 		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	} 
	
	private class ButtonHandler implements ActionListener 
	{
		public void actionPerformed(ActionEvent evtE) 
		{
			if(evtE.getSource() == Jbtn_YES)
			{
				checkID_PW();
			}else if(evtE.getSource() == Jbtn_NO)
			{
				cleanID_PW();
			}
		}
	}
	
	//�^�Ǳb���r��
	public String getID()
	{
		return jid.getText();
	}
	
	//�^�ǱK�X�r���}�C
	public char [] getPW()
	{
		return jpw.getPassword();
	}
	
	public void cleanID_PW()
	{
		jid.setText("");
		jpw.setText("");
	}
	
	protected abstract void checkID_PW();
}