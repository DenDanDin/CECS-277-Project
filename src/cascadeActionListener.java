import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

/**
 * ActionListener for the Cascade button.
 */
public class cascadeActionListener implements ActionListener {
    private App a;

    /**
     * Constructor that passes in an App.
     * (Used to access the ArrayList in App).
     * @param a - the App.
     */
    public cascadeActionListener(App a){
        this.a = a;
    }

    /**
     * Cascades all the windows visible in the project.
     * @param e - the event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = a.list_ff.size()-1; i >= 0; i--){   //remove the nonvisible (deleted) windows.
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
        try {
            a.list_ff.get(a.list_ff.size()-1).setSelected(true);
        } catch (PropertyVetoException propertyVetoException) {
            propertyVetoException.printStackTrace();
        }
    }
}
