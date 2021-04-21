import javax.swing.JPanel;
import javax.swing.JList;
import java.io.File;

public class FilePanel extends JPanel {
    private JList myList;
    private DirPanel dir;
    public FilePanel(DirPanel dir){
        this.dir = dir;
        showFiles();
    }

    public void showFiles(){
        if(dir.nodeSelected != null) {
            System.out.println("True");
            File[] files = dir.nodeSelected.getFile().listFiles();
            String[] names = new String[files.length];
            for (int i = 0; i < names.length; i++) {
                names[i] = files[i].getAbsolutePath();
            }
            if (files != null) {
                System.out.println("True");
                myList = new JList(names);
                this.add(myList);
            }
        }
    }


}
