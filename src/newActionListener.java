import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class newActionListener implements ActionListener {
    private App a;

    public newActionListener(App a){
        this.a = a;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        FileFrame ff = new FileFrame();
        ff.setTitle(a.currentDrive);
        ff.setLocation(a.desktop.getX(), a.desktop.getY()+100);
        a.list_ff.add(ff);
        a.desktop.add(ff);
    }
}
