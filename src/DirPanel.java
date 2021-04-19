import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import java.awt.*;

/**
 * JPanel for the Directory Window.
 */
public class DirPanel extends JPanel{
    private JScrollPane scrollpane = new JScrollPane();
    private JTree dirtree = new JTree();

    /**
     * Adds a scrollpane and a JTree to the directory window.
     */
    public DirPanel(){
        this.setLayout(new BorderLayout());
        scrollpane.setViewportView(dirtree);
        scrollpane.setSize(this.getSize());
        this.add(scrollpane, BorderLayout.CENTER);
    }

}
