import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener for the New button.
 */
public class newActionListener implements ActionListener {
    private App a;

    /**
     * Constructor that passes an App.
     * (App is used to access currentDrive and desktop.. change later).
     * @param a - the App.
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
        FileFrame ff = new FileFrame(a);
        ff.setTitle(a.currentDrive);
        ff.setLocation(a.desktop.getX(), a.desktop.getY()+100);
        a.list_ff.add(ff);
        a.desktop.add(ff);
    }
}
