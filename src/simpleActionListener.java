import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener for the Simple button.
 * @author Daniel De Guzman and Andy Wong
 */
public class simpleActionListener implements ActionListener {

    App app;

    /**
     * Constructor for simpleActionListener.
     * @param a - the app to get the selected frame.
     */
    public simpleActionListener(App a){
        app = a;
    }
    /**
     * Displays the FilePanel with only the file name.
     * @param e - the event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Simple")) {
            System.out.println("Simple Pressed");
            app.frame.right.showFileSimple();
        }
    }
}
