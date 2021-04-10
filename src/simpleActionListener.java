import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class simpleActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Simple")) {
            System.out.println("You pressed Simple");
        }
    }
}
