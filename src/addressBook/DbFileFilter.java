//此程式參考「良葛格-JNotePad - 簡單的文字編輯器」
//參考網址：http://caterpillar.onlyfun.net/Gossip/index.html
//名稱：檔案過瀘

package addressBook;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class DbFileFilter extends FileFilter
{
    public boolean accept(File file)
    {
    	//判斷是否為目錄
        if(file.isDirectory())
            return true;
        
        //判斷是否有副檔名
        int i = file.getName().lastIndexOf('.');
        
        if(i == -1)
            return false;
        
        //判斷副檔名是否正確
        String extname = file.getName().substring(i).toLowerCase();
        if(extname.equals(".txt"))
            return true;
                                                                                
        return false;
    }
                                                                                
    public String getDescription() {
        return "*.txt";
    }
}