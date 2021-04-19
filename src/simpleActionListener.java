import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener for the Simple button.
 */
public class simpleActionListener implements ActionListener {
    /**
     * For now, displays a msg to console saying "You Pressed Simple".
     * This will be different in the future.
     * @param e - the event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Simple")) {
            System.out.println("You pressed Simple");
        }
    }
}
