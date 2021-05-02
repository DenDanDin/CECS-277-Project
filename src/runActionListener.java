import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * ActionListener for the Run function.
 * @author Daniel De Guzman and Andy Wong
 */
public class runActionListener implements ActionListener {

    App app;

    /**
     * Constructor for runActionListener.
     * @param a - the app to get information from.
     */
    public runActionListener(App a){
        app = a;
    }

    /**
     * Run the selected file.
     * @param e - the event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Desktop desktop = Desktop.getDesktop();
        int row = app.frame.right.tableOfFiles.getSelectedRow();
        String name = (String) app.frame.right.tableOfFiles.getModel().getValueAt(row, 1);
        MyFileNode file = (MyFileNode) app.frame.left.nodeSelected.getUserObject();
        try{
            File fileToOpen = new File(file.getFileName() + File.separator + name);
            desktop.open(fileToOpen);
        }catch(Exception ex){
            System.out.println("Not able to open");
        }
    }
}
