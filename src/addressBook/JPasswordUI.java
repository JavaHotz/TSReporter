//名稱：密碼輸入介面

package addressBook;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public abstract class JPasswordUI extends JFrame
{
	protected JLabel Jlb_ID = new JLabel("帳號：");
	protected JLabel Jlb_PW = new JLabel("密碼：");
	protected String key = "1535879468"; //加解密的key
	protected JAes aes = new JAes(); //aes加解密
	private JPasswordField jpw = new JPasswordField(8);
	private JTextField jid = new JTextField(10);
	private JButton Jbtn_YES = new JButton("    Ok    ");
	private JButton Jbtn_NO = new JButton("   Clean   ");
	private JButton Jbtn_Guest = new JButton("   Geust   ");
	private ButtonHandler hbtHandler = new ButtonHandler();  //處理按鈕事件 

	public JPasswordUI()
	{
		super("輸入帳號密碼"); 
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
		bag1.anchor = GridBagConstraints.NORTHWEST;
		 
		GridBagConstraints bag2 = new GridBagConstraints();
		bag2.gridx = 3;
		bag2.gridy = 0;
		bag2.gridwidth = 2;
		bag2.gridheight = 1;
		bag2.weightx = 0;
		bag2.weighty = 0;
		bag2.fill = GridBagConstraints.NONE;
		bag2.anchor = GridBagConstraints.NORTHWEST;
		 
		GridBagConstraints bag3 = new GridBagConstraints();
		bag3.gridx = 0;
		bag3.gridy = 2;
		bag3.gridwidth = 2;
		bag3.gridheight = 1;
		bag3.weightx = 0;
		bag3.weighty = 0;
		bag3.insets = new Insets(5,0,0,0);
		bag3.fill = GridBagConstraints.NONE;
		bag3.anchor = GridBagConstraints.NORTHWEST;
		
		GridBagConstraints bag4 = new GridBagConstraints();
		bag4.gridx = 3;
		bag4.gridy = 2;
		bag4.gridwidth = 2;
		bag4.gridheight = 1;
		bag4.weightx = 0;
		bag4.weighty = 0;
		bag4.insets = new Insets(5,0,0,0);
		bag4.fill = GridBagConstraints.BOTH;
		bag4.anchor = GridBagConstraints.NORTHWEST;
		
		GridBagConstraints bag5 = new GridBagConstraints();
		bag5.gridx = 1;
		bag5.gridy = 3;
		bag5.gridwidth = 4;
		bag5.gridheight = 1;
		bag5.weightx = 0;
		bag5.weighty = 0;
		bag5.insets= new Insets(15,0,0,0);
		bag5.fill = GridBagConstraints.NONE;
		bag5.anchor = GridBagConstraints.LAST_LINE_START;
		
		GridBagConstraints bag6 = new GridBagConstraints();
		bag6.gridx = 3;
		bag6.gridy = 3;
		bag6.gridwidth = 4;
		bag6.gridheight = 1;
		bag6.weightx = 0;
		bag6.weighty = 0;
		bag6.insets= new Insets(15,0,0,0);
		bag6.fill = GridBagConstraints.NONE;
		bag6.anchor = GridBagConstraints.LAST_LINE_END;
		
		GridBagConstraints bag7 = new GridBagConstraints();
		bag7.gridx = 1;
		bag7.gridy = 4;
		bag7.gridwidth = 4;
		bag7.gridheight = 1;
		bag7.weightx = 0;
		bag7.weighty = 0;
		bag7.insets= new Insets(0,0,0,0);
		bag7.fill = GridBagConstraints.NONE;
		bag7.anchor = GridBagConstraints.CENTER;
		
		//設定Jlb_ID大小位置及顯示字型
		Jlb_ID.setLocation(20,10);
		Jlb_ID.setSize(100,40);
		Jlb_ID.setFont(new Font("Serif",Font.BOLD,24));
		c.add(Jlb_ID, bag1);
		
		//設定帳號輸入框大小位置及顯示字型
		jid.setLocation(70,10);
		jid.setSize(200,40);
		jid.setFont(new Font("Serif",Font.BOLD,24));
		c.add(jid, bag2);
		
		//設定Jlb_PW大小位置及顯示字型
		Jlb_PW.setLocation(20,50);
		Jlb_PW.setSize(100,40);
		Jlb_PW.setFont(new Font("Serif",Font.BOLD,24));
		c.add(Jlb_PW, bag3);
		
		//設定密碼輸入框大小位置及顯示字型
		jpw.setLocation(70,50);
		jpw.setSize(200,40);
		jpw.setFont(new Font("Serif",Font.BOLD,24));
		jpw.setEchoChar('●');
		jpw.setToolTipText("密碼");
		c.add(jpw, bag4);
		
		jpw.addActionListener(hbtHandler);
				
		//設定確定按鈕大小位置及顯示字型
		Jbtn_YES.setLocation(10,270);
		Jbtn_YES.setSize(200,50);
		Jbtn_YES.addActionListener(hbtHandler);
		Jbtn_YES.setFont(new Font("Serif", Font.PLAIN, 24));
		c.add(Jbtn_YES, bag5);
		//設定清除按鈕大小位置及顯示字型
		Jbtn_NO.setLocation(350,270);
		Jbtn_NO.setSize(200,50);
		Jbtn_NO.addActionListener(hbtHandler);
		Jbtn_NO.setFont(new Font("Serif", Font.PLAIN, 24));
		c.add(Jbtn_NO, bag6);
		//設定清除按鈕大小位置及顯示字型
		Jbtn_Guest.setLocation(180,270);
		Jbtn_Guest.setSize(200,50);
		Jbtn_Guest.addActionListener(hbtHandler);
		Jbtn_Guest.setFont(new Font("Serif", Font.PLAIN, 24));
		c.add(Jbtn_Guest, bag7);
		
		//設定視窗
		setSize(400,300);
		setLocation(400,200); 
 		setResizable(false);//視窗放大按鈕無效 
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
			else if(evtE.getSource() == Jbtn_Guest)
			{
				new JAddressBook();
				setVisible(false);
				return;
			}
		}
	}
	
	//回傳帳號字串
	public String getID()
	{
		return jid.getText();
	}
	
	//回傳密碼字元陣列
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