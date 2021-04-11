import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.*;

public class FileFrame extends JInternalFrame {

    JSplitPane splitpane;
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
