import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener for the Exit button.
 */
public class exitActionListener implements ActionListener {
    /**
     * Exits the program when button is pressed.
     * @param e - the event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
