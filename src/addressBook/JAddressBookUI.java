//�W�١G�q�T������
//�]�p�v�G�N�L��
//����G2008/07/24


package addressBook;

import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.*;


public abstract  class JAddressBookUI extends JFrame
{
        private ButtonHandler bh = new ButtonHandler();//�\����ƥ�
        private JMenuBar jmb;
        private JMenu jmu = new JMenu("�ɮ�"),about = new JMenu("����"),setting = new JMenu("�]�w");
        private JMenuItem [] jmi1 = new JMenuItem[4];//�ɮ׿��
        private JMenuItem [] abo = new JMenuItem[2];//������
	private JMenuItem [] set = new JMenuItem[1];//�]�w���
        private JButton insert = new JButton("�s�W");
        private JButton search = new JButton("�d��");
        private JButton delete = new JButton("�R��");
        private JPanel jpl = new JPanel(new GridLayout(1,3,30,0));
        private JComboBox jcbox;
        private String [][] db = readBook();
        protected String [] BookField = {"�m�W","�ͤ�","���","��a�q��","�Y�ɳq/MSN"};
        protected DefaultTableModel tmodel = new DefaultTableModel(db,BookField); //�إߪ��      
        protected JTable book = new JTable(tmodel); //�إ�JTable
        
        public JAddressBookUI()
        {
                super("�q�T��");
                db = readBook(); //Ū����Ʈw
                Container c = getContentPane();
                c.setLayout(new BorderLayout());
                jmb = new JMenuBar();
                this.setJMenuBar(jmb); //�[�J�u��C
                
                //�d�߿ﶵ�U�Ԧ����
                jcbox = new JComboBox(BookField);
                
                //�q�T�����\�ඵ��
                jmb.add(jmu);
                jmi1[0] = new JMenuItem("�x�s");
                jmi1[1] = new JMenuItem("�פJ�q�T��");
                jmi1[2] = new JMenuItem("�ץX�q�T��");
                jmi1[3] = new JMenuItem("�����{��");
                jmu.add(jmi1[0]);
                jmu.addSeparator();
                jmu.add(jmi1[1]);
                jmu.add(jmi1[2]);
                jmu.addSeparator();
                jmu.add(jmi1[3]);
                
                //�]�w��ƪ�å[�J�u��
                book.getColumnModel().getColumn(4).setPreferredWidth(180);
                book.getTableHeader().setReorderingAllowed(false); //����������\��
                book.setShowHorizontalLines(false); //����ܦC��u
                c.add(new JScrollPane(book),BorderLayout.CENTER);

		//�]�w����ܶ���
                jmb.add(setting);
                set[0] = new JMenuItem("�ק�b�K");
                setting.add(set[0]);

                //���󪺿�ܶ���
                jmb.add(about);
                abo[0] = new JMenuItem("�{������");
                abo[1] = new JMenuItem("�@��");
                about.add(abo[0]);
                about.add(abo[1]);

                jpl.add(insert);
                jpl.add(jcbox);
                jpl.add(search);
                jpl.add(delete);
                
                c.add(jpl,BorderLayout.NORTH);
                              
                //�]�w����
                setSize(500,400);
                setLocation(50,50); 
                setResizable(false);//������j���s�L�� 
                setVisible(true);
                
                //���U���������s�ƥ�B�z
                addWindowListener(
                    new WindowAdapter() {
                        public void windowClosing(WindowEvent e) { 
                        	exitBook();
                        }
                    }
                );
                      
                //���U�\�����ť��
                for(int m=0;m<jmi1.length;m++)
                	jmi1[m].addActionListener(bh);
                abo[0].addActionListener(bh);
                abo[1].addActionListener(bh);
		set[0].addActionListener(bh);
                
                //���U���s��ť��
                insert.addActionListener(bh);
                search.addActionListener(bh);
                delete.addActionListener(bh);             
        }
        
        //�\����ƥ�B�z
        private class ButtonHandler implements  ActionListener
        {
                public void actionPerformed(ActionEvent ae) 
                {                        
                        if(ae.getSource() == jmi1[0]) //�x�s
                        {
                        	saveBook();
                        }
                        else if(ae.getSource() == jmi1[1]) //�פJ�q�T��
                        {
                        	inputBook();
                        }
                        else if(ae.getSource() == jmi1[2]) //�ץX�q�T��
                        {
                        	outputBook();
                        }
                        else if(ae.getSource() == jmi1[3]) //�����{��
                        {
                        	exitBook();
                        }
                        else if(ae.getSource() == abo[0]) //�{������
                        {
                        	showAbout();
                        }
                        else if(ae.getSource() == abo[1]) //�@��
                        {
                        	showProgrammer();
                        }
			else if(ae.getSource() == set[0]) //�]�w�s�b�K
                        {
                        	setIDPW();
                        }
                        else if(ae.getSource() == insert) //�s�W
                        {
                        	insertData();
                        }
                        else if(ae.getSource() == search) //�Ωm�W�d��
                        {
                        	//�޼Ƭ��U�Կﶵ�����ޭ�
                        	dataSearch(jcbox.getSelectedIndex());
                        }
                        else if(ae.getSource() == delete) //�R��
                        {
                        	deleteData();                        	
                        }
                        
                }
        }
                
        protected abstract void saveBook(); //�x�s��ƪ���Ʈw
        protected abstract void saveBook(String path); //�]�w���|���x�s
        protected abstract String [][] readBook(); //Ū���w�]��Ʈw���ƪ�
        protected abstract String [][] readBook(String path); //Ū��path����Ʈw���ƪ�
        protected abstract void inputBook(); //�פJ�ɮ�
        protected abstract void outputBook(); //�ץX�ɮ�
        protected abstract void insertData(); //�s�W
        protected abstract void dataSearch(int op); //�H��Ķ���جd��
        protected abstract void deleteData(); //�R���浧���
        protected abstract void setIDPW(); //�x�s��ƪ���Ʈw
        protected abstract void showProgrammer(); //�������]�p��
        protected abstract void showAbout(); //��ܵ{������
        protected abstract void exitBook(); //�����{��
}