import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * ActionListener for the New button.
 * @author Daniel De Guzman and Andy Wong
 */
public class newActionListener implements ActionListener {
    private App a;

    /**
     * Constructor for the newActionListener.
     * @param a - the App (to get information from).
     */
    public newActionListener(App a){
        this.a = a;
    }

    /**
     * Creates a new FileFrame when the button is pressed.
     * @param e - the event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        File disk = (File) a.combo.getSelectedItem();
        a.currentDrive = disk.getAbsolutePath();
        FileFrame ff = new FileFrame(a);
        ff.setLocation(a.desktop.getX(), a.desktop.getY()+100);
        a.list_ff.add(ff);
        a.desktop.add(ff);
    }
}
