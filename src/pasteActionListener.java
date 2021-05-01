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
        String dir = app.pasteDir;
        File paste = new File(dir + File.separator + app.copyFile.getName());
        System.out.println("PASTE: " + paste.getAbsolutePath());
        System.out.println("PASTE FRAME:  " + app.frame.title);
        try{
            Files.copy(app.copyFile.toPath(), paste.toPath());
            app.frame.right.showFileDetails();
            app.copyFile = null;    //reset copyFile.
        } catch(Exception ex){
            System.out.println("Not able to copy");
        }

    }
}
