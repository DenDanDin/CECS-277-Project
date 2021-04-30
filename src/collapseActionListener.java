import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener for the collapse button.
 * @author Daniel De Guzman and Andy Wong
 */
public class collapseActionListener implements ActionListener {
	
    FileFrame frame;
	/**
		Constructor for collapseActionListener.
		
		@param frame - the frame that contains the branch to collapse.
	*/
    public collapseActionListener(FileFrame frame){
        this.frame = frame;
    }

	/**
		Collapse the branch in the DirPanel.
		
		@param e - the event.
	*/
    @Override
    public void actionPerformed(ActionEvent e) {
        if(frame.isSelected() == true) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) frame.left.nodeSelected;
            TreePath path = new TreePath(node.getPath());
            frame.left.dirtree.collapsePath(path);
        }
    }
}
