import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class deleteActionListener implements ActionListener {

    App app;

    public deleteActionListener(App a){
        app = a;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("delete");
        int row = app.frame.right.tableOfFiles.getSelectedRow();
        String name = (String) app.frame.right.tableOfFiles.getModel().getValueAt(row, 0);
        MyFileNode drive = (MyFileNode) app.frame.left.nodeSelected.getUserObject();
        File deleteFile = new File(drive.getFileName() + File.separator + name);
        app.frame.right.model.removeRow(row);
        deleteFile.delete();
    }
}
