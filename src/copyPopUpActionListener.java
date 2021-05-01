import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class copyPopUpActionListener implements ActionListener {

    App app;
    public copyPopUpActionListener(App a){
        app = a;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("copy");
        int row = app.frame.right.tableOfFiles.getSelectedRow();
        String name = (String) app.frame.right.tableOfFiles.getModel().getValueAt(row, 0);
        MyFileNode file = (MyFileNode) app.frame.left.nodeSelected.getUserObject();
        app.copyFile = new File(file.getFileName() + File.separator + name);
        System.out.println("COPY: " + app.copyFile.getAbsolutePath());
    }
}
