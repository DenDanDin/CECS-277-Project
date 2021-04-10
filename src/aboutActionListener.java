import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class aboutActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        About_Dialog about = new About_Dialog(null, true);
        about.setVisible(true);
    }
}
