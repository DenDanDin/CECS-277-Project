import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JOptionPane;

public class deleteActionListener implements ActionListener {

    App app;
    JOptionPane deleting;

    public deleteActionListener(App a){
        app = a;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("PopUp Menu --> Delete");
        int row = app.frame.right.tableOfFiles.getSelectedRow();
        String name = (String) app.frame.right.tableOfFiles.getModel().getValueAt(row, 0);
        File deleteFile = new File(app.frame.title + File.separator + name);
        deleting = new JOptionPane();
        int choice = JOptionPane.showConfirmDialog(null, "Delete " + deleteFile.getAbsolutePath(), "Deleting!!", JOptionPane.YES_NO_OPTION);
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
