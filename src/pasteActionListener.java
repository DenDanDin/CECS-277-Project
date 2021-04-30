import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;

public class pasteActionListener implements ActionListener {

    App app;
    public pasteActionListener(App a){
        app = a;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("paste");
        MyFileNode drive = (MyFileNode) app.frame.left.nodeSelected.getUserObject();
        String newLocation = drive.getFile().getAbsolutePath();
        File paste = new File(newLocation + File.separator + app.copyFile.getName());
        System.out.println(paste.getAbsolutePath());
        try{
            Files.copy(app.copyFile.toPath(), paste.toPath());
            app.frame.right.showFileDetails();
            //ADD TO FILE FRAME.
            //app.copyFile = null;
        } catch(Exception ex){
            System.out.println("Not able to copy");
        }

    }
}
