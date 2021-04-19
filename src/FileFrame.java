import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import java.awt.*;

/**
 * JInternalFrame for the Left and Right Side of the Directory Window.
 */
public class FileFrame extends JInternalFrame {

    JSplitPane splitpane;

    /**
     * Creates a splitplane where the left side will be
     * the Directory Panel, and the right side will be the File Panel.
     */
    public FileFrame(){
        this.setLayout(new BorderLayout());
        splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new DirPanel(), new FilePanel());
        splitpane.setSize(600,400);
        this.getContentPane().add(splitpane);
        this.setClosable(true);
        this.setMaximizable(true);
        this.setIconifiable(true);
        this.setSize(700,500);
        this.setVisible(true);
    }

}
