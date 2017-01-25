//���{���Ѧҡu�}����-JNotePad - ²�檺��r�s�边�v
//�ѦҺ��}�Ghttp://caterpillar.onlyfun.net/Gossip/index.html
//�W�١G�ɮ׹L�y

package addressBook;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class DbFileFilter extends FileFilter
{
    public boolean accept(File file)
    {
    	//�P�_�O�_���ؿ�
        if(file.isDirectory())
            return true;
        
        //�P�_�O�_�����ɦW
        int i = file.getName().lastIndexOf('.');
        
        if(i == -1)
            return false;
        
        //�P�_���ɦW�O�_���T
        String extname = file.getName().substring(i).toLowerCase();
        if(extname.equals(".txt"))
            return true;
                                                                                
        return false;
    }
                                                                                
    public String getDescription() {
        return "*.txt";
    }
}