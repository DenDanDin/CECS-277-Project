import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class DirPanel extends JPanel{
    private JScrollPane scrollpane = new JScrollPane();
    private JTree dirtree;
    App a;
    public DirPanel(App a){
        this.a = a;
        this.setLayout(new BorderLayout());
        buildTree();
        scrollpane.setViewportView(dirtree);
        scrollpane.setSize(this.getSize());
        this.add(scrollpane, BorderLayout.CENTER);
    }
    public void buildTree(){
        DefaultMutableTreeNode top = new DefaultMutableTreeNode(a.currentDrive);
        dirtree = new JTree(top);
        String dirName;
        DefaultMutableTreeNode directory;
        for(int i = 0; i < a.files.length; i++){
            dirName = a.files[i].getAbsolutePath();
            directory = new DefaultMutableTreeNode(dirName);
            top.add(directory);
        }
    }


}
