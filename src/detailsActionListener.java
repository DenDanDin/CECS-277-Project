import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener for the Details button.
 */
public class detailsActionListener implements ActionListener {

    FileFrame frame;

    /**
     * Constructor for detailsActionListener
     * @param frame - the frame to edit when button is pressed.
     */
    public detailsActionListener(FileFrame frame){
        this.frame = frame;
    }
    /**
     * Displays the FilePanel with the file details
     * (name, date modified, size).
     * @param e - the event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Details")) {
            frame.right.showFileDetails();
        }
    }
}
