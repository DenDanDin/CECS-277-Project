import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener for the Details button.
 */
public class detailsActionListener implements ActionListener {
    /**
     * For now, just prints a msg to console saying "You Pressed Details".
     * @param e - the event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Details")) {
            System.out.println("You pressed Details");
        }
    }
}
