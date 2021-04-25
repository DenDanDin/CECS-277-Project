import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class collapseActionListener implements ActionListener {
    FileFrame frame;
    public collapseActionListener(FileFrame frame){
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(frame.isSelected() == true) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) frame.left.nodeSelected;
            TreePath path = new TreePath(node.getPath());
            frame.left.dirtree.collapsePath(path);
        }
    }
}
