import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class runActionListener implements ActionListener {

    FileFrame frame;
    public runActionListener(FileFrame f){
        frame = f;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Desktop desktop = Desktop.getDesktop();
        int row = frame.right.tableOfFiles.getSelectedRow();
        String name = (String) frame.right.tableOfFiles.getModel().getValueAt(row, 0);
        MyFileNode file = (MyFileNode) frame.left.nodeSelected.getUserObject();
        try{
            File fileToOpen = new File(file.getFileName() + File.separator + name);
            desktop.open(fileToOpen);
        }catch(Exception ex){
            System.out.println("Not able to open");
        }
    }
}
