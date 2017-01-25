//�W�١G�q�T��
//�]�p�v�G�N�L��
//����G2008/07/24

package addressBook;
import java.io.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.Arrays;

public class JAddressBook extends JAddressBookUI
{
	private JFileChooser fc;
	private JSetting js; //�ק�b�K

	public JAddressBook()
	{
		//�إ��ɮ׹L�o��
        fc = new JFileChooser();
        fc.addChoosableFileFilter(new DbFileFilter());
	}
	
	//�x�s
    protected void saveBook() 
    {
    	int op = JOptionPane.showConfirmDialog(null, 
				"�T�w�x�s�q�T��?", "�T��", JOptionPane.YES_NO_OPTION);
		if(op == JOptionPane.NO_OPTION)
			return;
		
    	//�إ߼g�J�ɮת�����
    	File f = new File("addressBook");
	String pathStr = "db/friendData.jdb";    	
    	saveBook(pathStr);
    }
    
    //�x�s���w���|��Ʈw
    protected void saveBook(String path)
    {
    	PrintWriter outs = null;
    	String tempRow = ""; //�Ȧs�浧���
    	String [][] tableArray = tableToArray(tmodel);
    	
    	try{
    		outs = new PrintWriter(new FileWriter(path));
    		for(int row = 0; row < tableArray.length; row++)
    		{
    			tempRow = "";
    			for(int col = 0; col < tableArray[row].length; col++)
   					tempRow += tableArray[row][col] + ",";
    			tempRow = tempRow.substring(0,tempRow.lastIndexOf(","));//�h���̫᪺���j�Ÿ�
    			outs.println(tempRow); //�g�J�浧���
    		}
        	JOptionPane.showMessageDialog(null,"�w���\�x�s�q�T�����","�T��",JOptionPane.INFORMATION_MESSAGE);  	
    	}catch(IOException e)
    	{
            JOptionPane.showMessageDialog(null, e.toString(),
                    "�x�s�q�T������", JOptionPane.ERROR_MESSAGE);
    	}
    	finally
    	{
    		outs.close(); //�����s��   		
    	}
    }
    
    //Ū���w�]��Ʈw
    protected String [][] readBook()
    {
	String pathStr = "db/friendData.jdb";
    	return readBook(pathStr);
    }
    
    //Ū��path����Ʈw
    protected String [][] readBook(String path)
    {
    	BufferedReader in = null;
    	String friendData = "";
    	String [][] tempTable = null;
    	
    	try
    	{
    		//�p��nŪ�J����Ƶ���
    		int count = 0;
        	in = new BufferedReader(new FileReader(path));//�إ�Ū���ɮת�����
    		while((friendData = in.readLine()) != null)
    			count++;
    		in.close();
    		
    		tempTable = new String[count][];
        	in = new BufferedReader(new FileReader(path));//�إ�Ū���ɮת�����
        	
        	int row = 0;
    		while((friendData = in.readLine()) != null)
    		{
    			tempTable[row++] = friendData.split(",");
    		}
    	}
    	catch(IOException e)
    	{
            JOptionPane.showMessageDialog(null, e.toString(),
                    "Ū����Ʈw����", JOptionPane.ERROR_MESSAGE);
    	}
    	
    	return tempTable;
    }
    
    //�פJ�ɮ�
    protected  void inputBook() 
    {
    	int option = fc.showDialog(null, null);

        //�T�{yes��no
    	if(option == JFileChooser.APPROVE_OPTION)
        {
            File file = fc.getSelectedFile();          
            tmodel.setDataVector(readBook(file.toString()),BookField); //��s��ƪ�
        }
    }
    
    //�ץX�q�T��
    protected void outputBook() 
    {
    	int option = fc.showDialog(null, null);

        //�T�{yes��no
    	if(option == JFileChooser.APPROVE_OPTION)
        {
            File file = fc.getSelectedFile();
            String saveFileName = (file.toString() + ".txt");            
            saveBook(saveFileName);
        }
    }
    
    //�s�W�@�ťո�ƦC
    protected void insertData() 
    {
    	tmodel.insertRow(0,new String[]{"N","N","N","N","N"});
    }
    
    //�d��
    protected void dataSearch(int op) 
    {
    	String na = JOptionPane.showInputDialog("�п�J���d�ߦn�ͪ�" + BookField[op]);
    	
    	if(na == null) 
    		return;
    	int index = linearSearch(tmodel,na,op);
    	
    	if(index == -1)
    		JOptionPane.showMessageDialog(null,"�S���ŦX���n�͸��","�T��",JOptionPane.INFORMATION_MESSAGE);
    	else
    		book.setRowSelectionInterval(index, index); //����j�M�쪺��Ƶ�
    }
        
    //�R���ҿ諸��Ƶ�
    protected void deleteData() 
    {
    	if(book.getSelectedRow() != -1)
    	{
    		int op = JOptionPane.showConfirmDialog(null, 
    				"�T�w�R��" + book.getSelectedRowCount() + "�����?", "�T��", JOptionPane.YES_NO_OPTION);
    		if(op == JOptionPane.YES_OPTION)
    		{   //���o�Ҧ����R���C�����ޭ�
    			int [] row = book.getSelectedRows();
    			
    			for(int r = 0; r < row.length; r++)
    			{
    				tmodel.removeRow(row[r]);//�R��1�C�ɡA�ѤU���C�ݴ�1
    				for(int i = r; i < row.length; i++ )
    					row[i]--;    				
    			}
    		}
    		
    	}
    	else
    		JOptionPane.showMessageDialog(null,"�п���@�����","�T��",JOptionPane.INFORMATION_MESSAGE);	
    }
    
    //����]�p��
    protected void showProgrammer()
    {
        JOptionPane.showMessageDialog(null,
        	"�{���]�p�G�N�L��\n"+
                "http://blog.xuite.net/ray00000test/blog" ,
                "�@��",JOptionPane.INFORMATION_MESSAGE);
    }

    //�����ƶ�
    protected void showAbout()
    {
        JOptionPane.showMessageDialog(null,
        	"�����ƶ��G\n"+
                "1.�s�W�\��G�s�W�@���ťզC\n" +
                "2.�s��\��G���I���U���ק諸���\n" +
                "3.�R���\��G�R���ҿ�Ķ�Ƶ����\n" +
                "4.�d�ߥ\��G�̴��ܿ�J��ơA�N�j�M�ø���ӵ���ƦC\n" +
                "5.�x�s�\��G�x�s�ثe��ܪ��q�T�����\n" +
                "6.�פJ(�X)�\��G�פJ(�X)�q�T���Ҧ���ƨæs�ɬ�txt\n" +
                "7.�m�W�i���ơA��ĳ���n��J���Ʃm�W\n" + 
		"8.�b�K�\��A�i�ק�b�K\n" + 
                "9.v1.1" ,
                "���󥻵{��",JOptionPane.INFORMATION_MESSAGE);
    }
    
    //�`�Ƿj�M�k(�ϥνçL)
    private int linearSearch(DefaultTableModel dtm,String key,int option)
    {
    	final int N = dtm.getRowCount();
        String [] tmp = new String[N + 1];
        
        for(int i = 0; i < N; i++)
        	tmp[i] = (String)dtm.getValueAt(i,option);
        tmp[N] = key; //�]�çL
        int i = 0;
        while(!key.equals(tmp[i]))
        	i++;
        
        //����Ʀ^�ǦC��m�A�Ϥ��^��-1
        if (i < N )
        	return i;
        return -1;  
    }
    
    //�N��ƪ�s���G���}�C
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
    
    //�����{��
    protected void exitBook()
    {
        if(isFileEdit()) 
        {
            dispose();
        }
        else 
        {
            int msg = JOptionPane.showConfirmDialog(
                    null, "�q�T���w�ק�A�O�_�x�s?",
                    "�x�s�q�T��?", JOptionPane.YES_NO_OPTION, 
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

    //�P�_�ק�b�K�����O�_�w�إߨó]�w���
    protected void setIDPW()
    {
	if(js == null)
		js = new JSetting();
	js.setVisible(true);
    }

    //�P�_��ƪ�O�_���ק�L
    private boolean isFileEdit()
    {
    	//���o�ثe��ܪ����
    	String [][] beforeDb = readBook(); //�ק�e��ƪ�
    	String [][] afterDb = tableToArray(tmodel); //�ק���ƪ�

    	//���쥻����ƪ�P�ثe��ܪ���ƪ�O�_�@��
    	if(Arrays.deepEquals(beforeDb, afterDb))
    		return true;

    	return false;
    }
}
