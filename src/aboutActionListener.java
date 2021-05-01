import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action Listener for the About Button.
 * @author Daniel De Guzman and Andy Wong
 */
public class aboutActionListener implements ActionListener {
	
    /**
     * Creates a new About_Dialog box.
     * @param e - the event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        About_Dialog about = new About_Dialog(null, true);
        about.setVisible(true);
    }
}
