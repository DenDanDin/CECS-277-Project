import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

public class cascadeActionListener implements ActionListener {
    private App a;
    public cascadeActionListener(App a){
        this.a = a;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = a.list_ff.size()-1; i >= 0; i--){
            if(a.list_ff.get(i).isVisible() == false){
                a.list_ff.remove(i);
            }
        }
        int x = a.topPanel.getX();
        int y = a.topPanel.getY();
        for(int i = 0; i < a.list_ff.size(); i++){
            a.list_ff.get(i).setLocation(x,y);
            x += 25;
            y += 25;
        }
    }
}
