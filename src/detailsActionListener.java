import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class detailsActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Details")) {
            System.out.println("You pressed Details");
        }
    }
}
