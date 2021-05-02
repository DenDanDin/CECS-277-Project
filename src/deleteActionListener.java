import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JOptionPane;

/**
 * ActionListener for the Delete function.
 * @author Daniel De Guzman and Andy Wong
 */
public class deleteActionListener implements ActionListener {

    App app;
    JOptionPane deleting;

    /**
     * Constructor for deleteActionListener.
     * @param a - the app to get information from.
     */
    public deleteActionListener(App a){
        app = a;
    }

    /**
     * Asks user to confirm if they want to delete the selected file.
     * @param e - the event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Delete Pressed");
        int row = app.frame.right.tableOfFiles.getSelectedRow();
        String name = (String) app.frame.right.tableOfFiles.getModel().getValueAt(row, 1);
        File deleteFile = new File(app.frame.title + File.separator + name);
        deleting = new JOptionPane();
        int choice = JOptionPane.showConfirmDialog(app, "Delete " + deleteFile.getAbsolutePath(), "Deleting!!", JOptionPane.YES_NO_OPTION);
        if(choice == JOptionPane.YES_OPTION){
            System.out.println("Deleting File");
            app.frame.right.model.removeRow(row);
            deleteFile.delete();
        }
        else{
            System.out.println("Canceled Deletion");
        }
    }
}
