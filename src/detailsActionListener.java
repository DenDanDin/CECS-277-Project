import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener for the Details button.
 * @author Daniel De Guzman and Andy Wong
 */
public class detailsActionListener implements ActionListener {

    App app;

    /**
     * Constructor for detailsActionListener
     * @param a - the app to get the selected frame.
     */
    public detailsActionListener(App a){
        app = a;
    }
    /**
     * Displays the FilePanel with the file details
     * (name, date modified, size).
     * @param e - the event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Details")) {
            app.frame.right.showFileDetails();
        }
    }
}
