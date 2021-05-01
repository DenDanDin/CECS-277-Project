import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener for the Expand Branch function.
 * @author Daniel De Guzman and Andy Wong
 */
public class expandActionListener implements ActionListener {

    App app;

    /**
     * Constructor for expandActionListener
     * @param a - the app to get the selected frame.
     */
    public expandActionListener(App a){
        app = a;
    }

    /**
     * Expand the branch selected.
     * @param e - the event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(app.frame.isSelected() == true) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) app.frame.left.nodeSelected;
            TreePath path = new TreePath(node.getPath());
            app.frame.left.dirtree.expandPath(path);
        }
    }
}
