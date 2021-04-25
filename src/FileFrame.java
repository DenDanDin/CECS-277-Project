import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import javax.swing.event.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreePath;
import java.awt.*;

/**
 * JInternalFrame for the Left and Right Side of the Directory Window.
 */
public class FileFrame extends JInternalFrame {

    JSplitPane splitpane;
    DirPanel left;
    FilePanel right;
    DefaultMutableTreeNode nodeSelected;

    /**
     * Creates a splitplane where the left side will be
     * the Directory Panel, and the right side will be the File Panel.
     */
    public FileFrame(App a){
        this.setLayout(new BorderLayout());
        MyFileNode drive = new MyFileNode(a.currentDrive);
        nodeSelected = new DefaultMutableTreeNode(drive);
        left = new DirPanel();
        left.dirtree.addTreeSelectionListener(new FileFrameListener());
        left.dirtree.addTreeWillExpandListener(new FileFrameListener());
        right = new FilePanel(nodeSelected);
        //right.myList.addListSelectionListener(new FileFrameListener());
        splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left, right);
        splitpane.setSize(600,400);
        this.getContentPane().add(splitpane);
        this.setClosable(true);
        this.setMaximizable(true);
        this.setIconifiable(true);
        this.setSize(700,500);
        this.setVisible(true);
    }

    class FileFrameListener implements TreeSelectionListener, TreeWillExpandListener, ListSelectionListener {

        @Override
        public void treeWillExpand(TreeExpansionEvent event) {
            TreePath e = event.getPath();
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getLastPathComponent();
            left.addChildren(node);
        }

        @Override
        public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {
        }

        @Override
        public void valueChanged(TreeSelectionEvent e) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) left.dirtree.getLastSelectedPathComponent();
            nodeSelected = node;
            right.showFiles(nodeSelected);
            MyFileNode mfn = (MyFileNode) node.getUserObject();
            //nodeSelected = mfn;
            System.out.println(mfn.toString() + "; isDirectory(): " + mfn.isDirectory() + "; isSub(): " + mfn.hasSubDirectory() + "; Class: " + mfn.getClass());
        }

        @Override
        public void valueChanged(ListSelectionEvent e) {
//            System.out.println("Node is a :  " + right.myList.getSelectedValue().getClass());
            System.out.println("True");
        }
    }

}
