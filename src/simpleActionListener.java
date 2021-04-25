import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener for the Simple button.
 */
public class simpleActionListener implements ActionListener {

    FileFrame frame;

    /**
     * Constructor for simpleActionListener.
     * @param frame - the frame to edit when button is pressed.
     */
    public simpleActionListener(FileFrame frame){
        this.frame = frame;
    }
    /**
     * Displays the FilePanel with only the file name.
     * @param e - the event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Simple")) {
            frame.right.showFileSimple();
        }
    }
}
