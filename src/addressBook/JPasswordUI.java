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
	private JButton Jbtn_YES = new JButton("Ok");
	private JButton Jbtn_NO = new JButton("Clean");
	private ButtonHandler hbtHandler = new ButtonHandler();  //�B�z���s�ƥ� 

	public JPasswordUI()
	{
		super("��J�b���K�X"); 
		Container c = getContentPane(); 
		c.setLayout(null);
		
		c.setLayout(new GridBagLayout());
		
		GridBagConstraints bag1 = new GridBagConstraints();
		bag1.gridx = 0;
		bag1.gridy = 0;
		bag1.gridwidth = 2;
		bag1.gridheight = 1;
		bag1.weightx = 0;
		bag1.weighty = 0;
		bag1.fill = GridBagConstraints.NONE;
		bag1.anchor = GridBagConstraints.WEST;
		 
		GridBagConstraints bag2 = new GridBagConstraints();
		bag2.gridx = 1;
		bag2.gridy = 0;
		bag2.gridwidth = 2;
		bag2.gridheight = 1;
		bag2.weightx = 0;
		bag2.weighty = 0;
		bag2.fill = GridBagConstraints.NONE;
		bag2.anchor = GridBagConstraints.WEST;
		 
		GridBagConstraints bag3 = new GridBagConstraints();
		bag3.gridx = 0;
		bag3.gridy = 1;
		bag3.gridwidth = 2;
		bag3.gridheight = 1;
		bag3.weightx = 0;
		bag3.weighty = 0;
		bag3.fill = GridBagConstraints.BOTH;
		bag3.anchor = GridBagConstraints.WEST;
		
		GridBagConstraints bag4 = new GridBagConstraints();
		bag4.gridx = 1;
		bag4.gridy = 1;
		bag4.gridwidth = 2;
		bag4.gridheight = 1;
		bag4.weightx = 0;
		bag4.weighty = 0;
		bag4.fill = GridBagConstraints.BOTH;
		bag4.anchor = GridBagConstraints.WEST;
		
		GridBagConstraints bag5 = new GridBagConstraints();
		bag5.gridx = 0;
		bag5.gridy = 2;
		bag5.gridwidth = 2;
		bag5.gridheight = 1;
		bag5.weightx = 0;
		bag5.weighty = 0;
		bag5.fill = GridBagConstraints.BOTH;
		bag5.anchor = GridBagConstraints.WEST;
		
		GridBagConstraints bag6 = new GridBagConstraints();
		bag6.gridx = 2;
		bag6.gridy = 2;
		bag6.gridwidth = 2;
		bag6.gridheight = 1;
		bag6.weightx = 0;
		bag6.weighty = 0;
		bag6.fill = GridBagConstraints.BOTH;
		bag6.anchor = GridBagConstraints.WEST;
		
		//�]�wJlb_ID�j�p��m����ܦr��
		Jlb_ID.setLocation(20,10);
		Jlb_ID.setSize(100,40);
		Jlb_ID.setFont(new Font("Serif",Font.BOLD,24));
		c.add(Jlb_ID, bag1);
		
		//�]�w�b����J�ؤj�p��m����ܦr��
		jid.setLocation(70,10);
		jid.setSize(200,40);
		jid.setFont(new Font("Serif",Font.BOLD,24));
		c.add(jid, bag2);
		
		//�]�wJlb_PW�j�p��m����ܦr��
		Jlb_PW.setLocation(20,50);
		Jlb_PW.setSize(100,40);
		Jlb_PW.setFont(new Font("Serif",Font.BOLD,24));
		c.add(Jlb_PW, bag3);
		
		//�]�w�K�X��J�ؤj�p��m����ܦr��
		jpw.setLocation(70,50);
		jpw.setSize(200,40);
		jpw.setFont(new Font("Serif",Font.BOLD,24));
		jpw.setEchoChar('��');
		jpw.setToolTipText("�K�X����8�Ӧr��");
		c.add(jpw, bag4);
		
		jpw.addActionListener(hbtHandler);
				
		//�]�w�T�w���s�j�p��m����ܦr��
		Jbtn_YES.setLocation(10,270);
		Jbtn_YES.setSize(160,40);
		Jbtn_YES.addActionListener(hbtHandler);
		Jbtn_YES.setFont(new Font("Serif", Font.PLAIN, 24));
		c.add(Jbtn_YES, bag5);
		
		//�]�w�M�����s�j�p��m����ܦr��
		Jbtn_NO.setLocation(350,270);
		Jbtn_NO.setSize(160,40);
		Jbtn_NO.addActionListener(hbtHandler);
		Jbtn_NO.setFont(new Font("Serif", Font.PLAIN, 24));
		c.add(Jbtn_NO, bag6);
		
		
		//�]�w����
		setSize(600,400);
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