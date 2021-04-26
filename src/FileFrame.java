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
    JInternalFrame frame;
    App a;
    /**
     * Creates a splitplane where the left side will be
     * the Directory Panel, and the right side will be the File Panel.
     */
    public FileFrame(App a){
        this.a = a;
        a.simple.addActionListener(new simpleActionListener(this));
        a.details.addActionListener(new detailsActionListener(this));
        a.expand_branch.addActionListener(new expandActionListener(this));
        a.collapse_branch.addActionListener(new collapseActionListener(this));
        frame = this;
        this.setLayout(new BorderLayout());
        MyFileNode drive = new MyFileNode(a.currentDrive);
        nodeSelected = new DefaultMutableTreeNode(drive);
        this.setTitle(drive.getFileName());
        left = new DirPanel(nodeSelected);
        left.dirtree.addTreeSelectionListener(new FileFrameListener());
        left.dirtree.addTreeWillExpandListener(new FileFrameListener());
        right = new FilePanel(nodeSelected);
        //right.myList.addListSelectionListener(new FileFrameListener());
        splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left, right);
        splitpane.setSize(600,400);

        this.addInternalFrameListener(new FileFrameListener());
        this.getContentPane().add(splitpane);
        this.setClosable(true);
        this.setMaximizable(true);
        this.setIconifiable(true);
        this.setSize(700,500);
        this.setVisible(true);
    }

    class FileFrameListener implements TreeSelectionListener, TreeWillExpandListener, ListSelectionListener, InternalFrameListener {

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
            left.nodeSelected = node;
            right.selectedNode = node;
            right.showFileDetails();
            MyFileNode mfn = (MyFileNode) node.getUserObject();
            frame.setTitle(mfn.getFileName());
            System.out.println(mfn.toString() + "; isDirectory(): " + mfn.isDirectory() + "; isSub(): " + mfn.hasSubDirectory() + "; Class: " + mfn.getClass());
        }

        @Override
        public void valueChanged(ListSelectionEvent e) {
//            System.out.println("Node is a :  " + right.myList.getSelectedValue().getClass());
            System.out.println("True");
        }

        @Override
        public void internalFrameOpened(InternalFrameEvent e) {
        }

        @Override
        public void internalFrameClosing(InternalFrameEvent e) {
        }

        @Override
        public void internalFrameClosed(InternalFrameEvent e) {
            a.list_ff.remove(this);
            System.out.println("Removed");
        }

        @Override
        public void internalFrameIconified(InternalFrameEvent e) {
        }

        @Override
        public void internalFrameDeiconified(InternalFrameEvent e) {
        }

        @Override
        public void internalFrameActivated(InternalFrameEvent e) {
            System.out.println("Activation");
            a.resetStatus();

        }

        @Override
        public void internalFrameDeactivated(InternalFrameEvent e) {
        }
    }

}
