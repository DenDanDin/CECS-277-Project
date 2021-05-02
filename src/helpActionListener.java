import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener for Help function in File Menu.
 * @author Daniel De Guzman and Andy Wong
 */
public class helpActionListener implements ActionListener {

    /**
     * Displays Help Screen.
     * @param e - the event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Help Pressed");
        Help_Dialog hd = new Help_Dialog(null, true);
        hd.setBoxTitle("Help --> View New Drive");
        hd.setHelpText("Change toolbar --> New Window");
        hd.setVisible(true);
    }
}
