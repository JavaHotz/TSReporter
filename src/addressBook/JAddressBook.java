//名稱：通訊錄
//設計師：吉他手
//日期：2008/07/24

package addressBook;
import java.io.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.Arrays;

public class JAddressBook extends JAddressBookUI
{
	private JFileChooser fc;
	private JSetting js; //修改帳密

	public JAddressBook()
	{
		//建立檔案過濾器
        fc = new JFileChooser();
        fc.addChoosableFileFilter(new DbFileFilter());
	}
	
	//儲存
    protected void saveBook() 
    {
    	int op = JOptionPane.showConfirmDialog(null, 
				"確定儲存通訊錄?", "訊息", JOptionPane.YES_NO_OPTION);
		if(op == JOptionPane.NO_OPTION)
			return;
		
    	//建立寫入檔案的物件
    	File f = new File("addressBook");
	String pathStr = "src/db/friendData.jdb";    	
    	saveBook(pathStr);
    }
    
    //儲存指定路徑資料庫
    protected void saveBook(String path)
    {
    	PrintWriter outs = null;
    	String tempRow = ""; //暫存單筆資料
    	String [][] tableArray = tableToArray(tmodel);
    	
    	try{
    		outs = new PrintWriter(new FileWriter(path));
    		for(int row = 0; row < tableArray.length; row++)
    		{
    			tempRow = "";
    			for(int col = 0; col < tableArray[row].length; col++)
   					tempRow += tableArray[row][col] + ",";
    			tempRow = tempRow.substring(0,tempRow.lastIndexOf(","));//去除最後的分隔符號
    			outs.println(tempRow); //寫入單筆資料
    		}
        	JOptionPane.showMessageDialog(null,"已成功儲存通訊錄資料","訊息",JOptionPane.INFORMATION_MESSAGE);  	
    	}catch(IOException e)
    	{
            JOptionPane.showMessageDialog(null, e.toString(),
                    "儲存通訊錄失敗", JOptionPane.ERROR_MESSAGE);
    	}
    	finally
    	{
    		outs.close(); //關閉存檔   		
    	}
    }
    
    //讀取預設資料庫
    protected String [][] readBook()
    {
	String pathStr = "src/db/friendData.jdb";
    	return readBook(pathStr);
    }
    
    //讀取path的資料庫
    protected String [][] readBook(String path)
    {
    	BufferedReader in = null;
    	String friendData = "";
    	String [][] tempTable = null;
    	
    	try
    	{
    		//計算要讀入的資料筆數
    		int count = 0;
        	in = new BufferedReader(new FileReader(path));//建立讀取檔案的物件
    		while((friendData = in.readLine()) != null)
    			count++;
    		in.close();
    		
    		tempTable = new String[count][];
        	in = new BufferedReader(new FileReader(path));//建立讀取檔案的物件
        	
        	int row = 0;
    		while((friendData = in.readLine()) != null)
    		{
    			tempTable[row++] = friendData.split(",");
    		}
    	}
    	catch(IOException e)
    	{
            JOptionPane.showMessageDialog(null, e.toString(),
                    "讀取資料庫失敗", JOptionPane.ERROR_MESSAGE);
    	}
    	
    	return tempTable;
    }
    
    //匯入檔案
    protected  void inputBook() 
    {
    	int option = fc.showDialog(null, null);

        //確認yes或no
    	if(option == JFileChooser.APPROVE_OPTION)
        {
            File file = fc.getSelectedFile();          
            tmodel.setDataVector(readBook(file.toString()),BookField); //更新資料表
        }
    }
    
    //匯出通訊錄
    protected void outputBook() 
    {
    	int option = fc.showDialog(null, null);

        //確認yes或no
    	if(option == JFileChooser.APPROVE_OPTION)
        {
            File file = fc.getSelectedFile();
            String saveFileName = (file.toString() + ".txt");            
            saveBook(saveFileName);
        }
    }
    
    //新增一空白資料列
    protected void insertData() 
    {
    	tmodel.insertRow(0,new String[]{"N","N","N","N","N"});
    }
    
    //查詢
    protected void dataSearch(int op) 
    {
    	String na = JOptionPane.showInputDialog("請輸入欲查詢好友的" + BookField[op]);
    	
    	if(na == null) 
    		return;
    	int index = linearSearch(tmodel,na,op);
    	
    	if(index == -1)
    		JOptionPane.showMessageDialog(null,"沒有符合的好友資料","訊息",JOptionPane.INFORMATION_MESSAGE);
    	else
    		book.setRowSelectionInterval(index, index); //跳到搜尋到的資料筆
    }
        
    //刪除所選的資料筆
    protected void deleteData() 
    {
    	if(book.getSelectedRow() != -1)
    	{
    		int op = JOptionPane.showConfirmDialog(null, 
    				"確定刪除" + book.getSelectedRowCount() + "筆資料?", "訊息", JOptionPane.YES_NO_OPTION);
    		if(op == JOptionPane.YES_OPTION)
    		{   //取得所有欲刪除列的索引值
    			int [] row = book.getSelectedRows();
    			
    			for(int r = 0; r < row.length; r++)
    			{
    				tmodel.removeRow(row[r]);//刪除1列時，剩下的列需減1
    				for(int i = r; i < row.length; i++ )
    					row[i]--;    				
    			}
    		}
    		
    	}
    	else
    		JOptionPane.showMessageDialog(null,"請選取一筆資料","訊息",JOptionPane.INFORMATION_MESSAGE);	
    }
    
    //關於設計者
    protected void showProgrammer()
    {
        JOptionPane.showMessageDialog(null,
        	"程式設計：吉他手\n"+
                "http://blog.xuite.net/ray00000test/blog" ,
                "作者",JOptionPane.INFORMATION_MESSAGE);
    }

    //說明事項
    protected void showAbout()
    {
        JOptionPane.showMessageDialog(null,
        	"說明事項：\n"+
                "1.新增功能：新增一筆空白列\n" +
                "2.編輯功能：請點選兩下欲修改的欄位\n" +
                "3.刪除功能：刪除所選譯數筆資料\n" +
                "4.查詢功能：依提示輸入資料，就搜尋並跳到該筆資料列\n" +
                "5.儲存功能：儲存目前顯示的通訊錄資料\n" +
                "6.匯入(出)功能：匯入(出)通訊錄所有資料並存檔為txt\n" +
                "7.姓名可重複，建議不要輸入重複姓名\n" + 
		"8.帳密功能，可修改帳密\n" + 
                "9.v1.1" ,
                "關於本程式",JOptionPane.INFORMATION_MESSAGE);
    }
    
    //循序搜尋法(使用衛兵)
    private int linearSearch(DefaultTableModel dtm,String key,int option)
    {
    	final int N = dtm.getRowCount();
        String [] tmp = new String[N + 1];
        
        for(int i = 0; i < N; i++)
        	tmp[i] = (String)dtm.getValueAt(i,option);
        tmp[N] = key; //設衛兵
        int i = 0;
        while(!key.equals(tmp[i]))
        	i++;
        
        //找到資料回傳列位置，反之回傳-1
        if (i < N )
        	return i;
        return -1;  
    }
    
    //將資料表存為二維陣列
    private String [][] tableToArray(DefaultTableModel dTable)
    {
    	String [][] tempTable = new String[dTable.getRowCount()][dTable.getColumnCount()];
    	
    	for(int row = 0; row < tempTable.length; row++)
    		for(int col = 0; col < tempTable[row].length; col++)
    		{
    			tempTable[row][col] = (String)dTable.getValueAt(row,col);
    			if(tempTable[row][col] == null)
    				tempTable[row][col] = "N";
    			if(tempTable[row][col].isEmpty())
    				tempTable[row][col] = "N";
    		}
    	
    	return tempTable;
    }
    
    //結束程式
    protected void exitBook()
    {
        if(isFileEdit()) 
        {
            dispose();
        }
        else 
        {
            int msg = JOptionPane.showConfirmDialog(
                    null, "通訊錄已修改，是否儲存?",
                    "儲存通訊錄?", JOptionPane.YES_NO_OPTION, 
                    JOptionPane.WARNING_MESSAGE, null);

            switch(msg) {
                case JOptionPane.YES_OPTION:
                	saveBook();
                    break;
                case JOptionPane.NO_OPTION:
                    dispose();
            }
        }
        System.exit(0);
    }

    //判斷修改帳密視窗是否已建立並設定顯示
    protected void setIDPW()
    {
	if(js == null)
		js = new JSetting();
	js.setVisible(true);
    }

    //判斷資料表是否有修改過
    private boolean isFileEdit()
    {
    	//取得目前顯示的資料
    	String [][] beforeDb = readBook(); //修改前資料表
    	String [][] afterDb = tableToArray(tmodel); //修改後資料表

    	//比對原本的資料表與目前顯示的資料表是否一樣
    	if(Arrays.deepEquals(beforeDb, afterDb))
    		return true;

    	return false;
    }
}