/**
 * ActionListener for Help function in File Menu.
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class helpActionListener implements ActionListener {
    /**
     * Displays Help Screen.
     * @param e - the event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Help_Dialog hd = new Help_Dialog(null, true);
        hd.setVisible(true);
    }
}
