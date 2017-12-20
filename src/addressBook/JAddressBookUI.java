//名稱：工單列表
//日期：2017/12/19


package addressBook;

import java.io.*;
import java.util.Enumeration;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.*;


public abstract  class JAddressBookUI extends JFrame
{
		public static String userID="";//儲存使用者名稱
		
        private ButtonHandler bh = new ButtonHandler();//功能表單事件
        private JMenuBar jmb;
        private JMenu jmu = new JMenu("檔案"),about = new JMenu("關於"),setting = new JMenu("設定");
        private JMenuItem [] jmi1 = new JMenuItem[4];//檔案選單
        private JMenuItem [] abo = new JMenuItem[2];//關於選單
	private JMenuItem [] set = new JMenuItem[1];//設定選單
        private JButton insert = new JButton("新增工單");
        private JButton search = new JButton("查詢資料");
        private JButton delete = new JButton("刪除資料");
        private JPanel jpl = new JPanel(new GridLayout(1,3,30,0));
        private JComboBox jcbox;
        private JComboBox jcTBbox;
        private String [][] db = readBook();
        protected String [] BookField = {"工單編號","計劃名稱","申請人","申請單位","試件名稱","批(序)號","總數","功測數","核批日期","完工日期","發文號","研判","主辦人","建檔日期"};
        protected DefaultTableModel tmodel = new DefaultTableModel(db,BookField); //建立表格      
        protected JTable book = new JTable(tmodel); //建立JTable
        
        public JAddressBookUI()
        {
                super("工單列表");
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
                jmi1[1] = new JMenuItem("匯入");
                jmi1[2] = new JMenuItem("匯出");
                jmi1[3] = new JMenuItem("離開");
                jmu.add(jmi1[0]);
                jmu.addSeparator();
                jmu.add(jmi1[1]);
                jmu.add(jmi1[2]);
                jmu.addSeparator();
                jmu.add(jmi1[3]);
                
                //設定資料表並加入滾輪
                book.getColumnModel().getColumn(0).sizeWidthToFit();
                book.getTableHeader().setReorderingAllowed(false); //關閉拖動欄位功能
                book.setShowHorizontalLines(false); //不顯示列橫線
                c.add(new JScrollPane(book),BorderLayout.CENTER);
                book.setRowHeight(30);
                book.setFont(new Font("Serif",Font.BOLD,18));book.getTableHeader().setFont(new Font("Serif",Font.BOLD,18));
                //橫軸捲軸
                book.setAutoResizeMode(book.AUTO_RESIZE_OFF);
                //欄寬自適調整
                FitTableColumns(book);
                //三角標誌, 向上為遞增排序, 向下為遞降排序.
                TableRowSorter sorter=new TableRowSorter(tmodel);
                book.setRowSorter(sorter);
                //Set custom price color renderer
                ChangeRowColorRenderer colorRenderer = new ChangeRowColorRenderer();
                book.setDefaultRenderer(Object.class, colorRenderer);
     
                
                //設定第12行
                jcTBbox = new JComboBox();
                jcTBbox.addItem("王勇謀");
                jcTBbox.addItem("許進財");
                jcTBbox.addItem("丁祥軒");
                jcTBbox.addItem("黃聖凱");
                jcTBbox.addItem("陳利彥");
                TableColumn column=book.getColumnModel().getColumn(12);
                column.setCellEditor(new DefaultCellEditor(jcTBbox));
                jcTBbox.setEditable(true);
                
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
                if(JAddressBookUI.getuserID().equals("test")){
                jpl.add(insert);
                jpl.add(jcbox);
                jpl.add(search);
                jpl.add(delete);

                insert.setFont(new Font("Serif",Font.BOLD,16));
                jcbox.setFont(new Font("Serif",Font.BOLD,16));
                search.setFont(new Font("Serif",Font.BOLD,16));
                delete.setFont(new Font("Serif",Font.BOLD,16));
                }
                else{
                jpl.add(jcbox);
                jpl.add(search);
                jcbox.setFont(new Font("Serif",Font.BOLD,16));
                search.setFont(new Font("Serif",Font.BOLD,16));
                book.setEnabled(false);
                }
                c.add(jpl,BorderLayout.NORTH);
                
                //設定視窗
                setSize(1024,768);
                setLocation(100,100); 
                //setResizable(false);//視窗放大按鈕無效 
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
                        else if(ae.getSource() == jmi1[1]) //匯入
                        {
                        	inputBook();
                        }
                        else if(ae.getSource() == jmi1[2]) //匯出
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
                        else if(ae.getSource() == search) //查詢
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
        //使用者id存取方法
        public static void setuserID(String str1) {
            userID = str1;
          }
        public static String getuserID() {
            return userID;
          }
        /**
         * 使jtable自適應列寬度
         * @param myTable
         */
        public static void FitTableColumns(JTable myTable) {
            JTableHeader header = myTable.getTableHeader();
            int rowCount = myTable.getRowCount();

            Enumeration columns = myTable.getColumnModel().getColumns();
            while (columns.hasMoreElements()) {
                TableColumn column = (TableColumn) columns.nextElement();
                int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
                int width = (int) myTable.getTableHeader().getDefaultRenderer()
                        .getTableCellRendererComponent(myTable, column.getIdentifier(), false, false, -1, col)
                        .getPreferredSize().getWidth();
                for (int row = 0; row < rowCount; row++) {
                    int preferedWidth = (int) myTable.getCellRenderer(row, col)
                            .getTableCellRendererComponent(myTable, myTable.getValueAt(row, col), false, false, row, col)
                            .getPreferredSize().getWidth();
                    width = Math.max(width, preferedWidth);
                }
                header.setResizingColumn(column);
                column.setWidth(width + myTable.getIntercellSpacing().width);
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