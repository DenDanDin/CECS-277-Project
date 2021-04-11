import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import java.awt.*;

public class DirPanel extends JPanel{
    private JScrollPane scrollpane = new JScrollPane();
    private JTree dirtree = new JTree();
    public DirPanel(){
        this.setLayout(new BorderLayout());
        scrollpane.setViewportView(dirtree);
        scrollpane.setSize(this.getSize());
        this.add(scrollpane, BorderLayout.CENTER);
    }

}
