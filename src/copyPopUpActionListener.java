import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * ActionListener for the "Copy" function in the PopUp menu.
 * @author Daniel De Guzman and Andy Wong
 */
public class copyPopUpActionListener implements ActionListener {

    App app;

    /**
     * Constructor for the copyPopUpActionListener
     * @param a - the app.
     */
    public copyPopUpActionListener(App a){
        app = a;
    }

    /**
     * Saves the file that the user wants to copy in App.
     * (File will be copied and pasted in pasteActionListener.java).
     * @param e - the event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("copy");
        int row = app.frame.right.tableOfFiles.getSelectedRow();
        String name = (String) app.frame.right.tableOfFiles.getModel().getValueAt(row, 1);
        MyFileNode file = (MyFileNode) app.frame.left.nodeSelected.getUserObject();
        app.copyFile = new File(file.getFileName() + File.separator + name);
        System.out.println("COPY: " + app.copyFile.getAbsolutePath());
    }
}
