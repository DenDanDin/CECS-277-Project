import javax.swing.JInternalFrame;

public class FileFrame extends JInternalFrame {


    public FileFrame(){
        this.setClosable(true);
        this.setMaximizable(true);
        this.setIconifiable(true);
        this.setSize(700,500);
        this.setVisible(true);
    }

}
