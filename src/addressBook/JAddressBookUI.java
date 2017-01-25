//名稱：通訊錄介面
//設計師：吉他手
//日期：2008/07/24


package addressBook;

import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.*;


public abstract  class JAddressBookUI extends JFrame
{
        private ButtonHandler bh = new ButtonHandler();//功能表單事件
        private JMenuBar jmb;
        private JMenu jmu = new JMenu("檔案"),about = new JMenu("關於"),setting = new JMenu("設定");
        private JMenuItem [] jmi1 = new JMenuItem[4];//檔案選單
        private JMenuItem [] abo = new JMenuItem[2];//關於選單
	private JMenuItem [] set = new JMenuItem[1];//設定選單
        private JButton insert = new JButton("新增");
        private JButton search = new JButton("查詢");
        private JButton delete = new JButton("刪除");
        private JPanel jpl = new JPanel(new GridLayout(1,3,30,0));
        private JComboBox jcbox;
        private String [][] db = readBook();
        protected String [] BookField = {"姓名","生日","手機","住家電話","即時通/MSN"};
        protected DefaultTableModel tmodel = new DefaultTableModel(db,BookField); //建立表格      
        protected JTable book = new JTable(tmodel); //建立JTable
        
        public JAddressBookUI()
        {
                super("通訊錄");
                db = readBook(); //讀取資料庫
                Container c = getContentPane();
                c.setLayout(new BorderLayout());
                jmb = new JMenuBar();
                this.setJMenuBar(jmb); //加入工具列
                
                //查詢選項下拉式選單
                jcbox = new JComboBox(BookField);
                
                //通訊錄的功能項目
                jmb.add(jmu);
                jmi1[0] = new JMenuItem("儲存");
                jmi1[1] = new JMenuItem("匯入通訊錄");
                jmi1[2] = new JMenuItem("匯出通訊錄");
                jmi1[3] = new JMenuItem("結束程式");
                jmu.add(jmi1[0]);
                jmu.addSeparator();
                jmu.add(jmi1[1]);
                jmu.add(jmi1[2]);
                jmu.addSeparator();
                jmu.add(jmi1[3]);
                
                //設定資料表並加入滾輪
                book.getColumnModel().getColumn(4).setPreferredWidth(180);
                book.getTableHeader().setReorderingAllowed(false); //關閉拖動欄位功能
                book.setShowHorizontalLines(false); //不顯示列橫線
                c.add(new JScrollPane(book),BorderLayout.CENTER);

		//設定的選擇項目
                jmb.add(setting);
                set[0] = new JMenuItem("修改帳密");
                setting.add(set[0]);

                //關於的選擇項目
                jmb.add(about);
                abo[0] = new JMenuItem("程式說明");
                abo[1] = new JMenuItem("作者");
                about.add(abo[0]);
                about.add(abo[1]);

                jpl.add(insert);
                jpl.add(jcbox);
                jpl.add(search);
                jpl.add(delete);
                
                c.add(jpl,BorderLayout.NORTH);
                              
                //設定視窗
                setSize(500,400);
                setLocation(50,50); 
                setResizable(false);//視窗放大按鈕無效 
                setVisible(true);
                
                //按下視窗關閉鈕事件處理
                addWindowListener(
                    new WindowAdapter() {
                        public void windowClosing(WindowEvent e) { 
                        	exitBook();
                        }
                    }
                );
                      
                //註冊功能表單傾聽者
                for(int m=0;m<jmi1.length;m++)
                	jmi1[m].addActionListener(bh);
                abo[0].addActionListener(bh);
                abo[1].addActionListener(bh);
		set[0].addActionListener(bh);
                
                //註冊按鈕傾聽者
                insert.addActionListener(bh);
                search.addActionListener(bh);
                delete.addActionListener(bh);             
        }
        
        //功能表單事件處理
        private class ButtonHandler implements  ActionListener
        {
                public void actionPerformed(ActionEvent ae) 
                {                        
                        if(ae.getSource() == jmi1[0]) //儲存
                        {
                        	saveBook();
                        }
                        else if(ae.getSource() == jmi1[1]) //匯入通訊錄
                        {
                        	inputBook();
                        }
                        else if(ae.getSource() == jmi1[2]) //匯出通訊錄
                        {
                        	outputBook();
                        }
                        else if(ae.getSource() == jmi1[3]) //結束程式
                        {
                        	exitBook();
                        }
                        else if(ae.getSource() == abo[0]) //程式說明
                        {
                        	showAbout();
                        }
                        else if(ae.getSource() == abo[1]) //作者
                        {
                        	showProgrammer();
                        }
			else if(ae.getSource() == set[0]) //設定新帳密
                        {
                        	setIDPW();
                        }
                        else if(ae.getSource() == insert) //新增
                        {
                        	insertData();
                        }
                        else if(ae.getSource() == search) //用姓名查詢
                        {
                        	//引數為下拉選項的索引值
                        	dataSearch(jcbox.getSelectedIndex());
                        }
                        else if(ae.getSource() == delete) //刪除
                        {
                        	deleteData();                        	
                        }
                        
                }
        }
                
        protected abstract void saveBook(); //儲存資料表到資料庫
        protected abstract void saveBook(String path); //設定路徑後儲存
        protected abstract String [][] readBook(); //讀取預設資料庫到資料表
        protected abstract String [][] readBook(String path); //讀取path的資料庫到資料表
        protected abstract void inputBook(); //匯入檔案
        protected abstract void outputBook(); //匯出檔案
        protected abstract void insertData(); //新增
        protected abstract void dataSearch(int op); //以選譯項目查詢
        protected abstract void deleteData(); //刪除單筆資料
        protected abstract void setIDPW(); //儲存資料表到資料庫
        protected abstract void showProgrammer(); //顯示關於設計者
        protected abstract void showAbout(); //顯示程式說明
        protected abstract void exitBook(); //結束程式
}