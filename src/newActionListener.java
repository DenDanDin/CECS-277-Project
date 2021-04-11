import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class newActionListener implements ActionListener {
    private App a;

    public newActionListener(App a){
        this.a = a;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        FileFrame ff = new FileFrame(a);
        ff.setTitle(a.currentDrive);
        ff.setLocation(a.desktopPane.getX(), a.desktopPane.getY()+100);
        a.list_ff.add(ff);
        a.desktopPane.add(ff);
    }
}
