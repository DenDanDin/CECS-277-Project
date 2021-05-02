import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;

/**
 * ActionListener for the Paste option in the popup menu.
 * @author Daniel De Guzman and Andy Wong
 */
public class pasteActionListener implements ActionListener {

    App app;

    /**
     * Constructor for a pasteActionListener.
     * @param a - the app to get information from.
     */
    public pasteActionListener(App a){
        app = a;
    }

    /**
     * Pastes the copied file to the current directory.
     * @param e - the event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Paste (PopUp) Pressed");
        String dir = app.frame.title;
        File paste = new File(dir + File.separator + app.copyFile.getName());
        try{
            Files.copy(app.copyFile.toPath(), paste.toPath());
            app.frame.right.showFileDetails();
            System.out.println("copyFile pasted to: " + paste.getAbsolutePath());
            app.copyFile = null;    //reset copyFile.
        } catch(Exception ex){
            System.out.println("Not able to copy");
        }

    }
}
