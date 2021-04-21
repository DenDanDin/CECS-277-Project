import com.sun.source.tree.Tree;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.*;
import javax.swing.tree.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

/**
 * JPanel for the Directory Window.
 */
public class DirPanel extends JPanel{
    private JScrollPane scrollpane = new JScrollPane();
    private JTree dirtree = new JTree();
    MyFileNode nodeSelected;

    /**
     * Adds a scrollpane and a JTree to the directory window.
     */
    public DirPanel(App a){
        this.setLayout(new BorderLayout());
        buildDirTree();
        dirtree.addTreeSelectionListener(new MyTreeListener());
        dirtree.addTreeWillExpandListener(new MyTreeListener());
        scrollpane.setViewportView(dirtree);
        scrollpane.setSize(this.getSize());
        this.add(scrollpane, BorderLayout.CENTER);
    }

    /**
     * Builds the Directory Tree.
     */
    public void buildDirTree(){
        MyFileNode drive = new MyFileNode("C:\\");
        File[] files = drive.getFile().listFiles();
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(drive);
        DefaultTreeModel treemodel = new DefaultTreeModel(root);

        lookLevelDown(root);
        addChildren(root);
        dirtree.setModel(treemodel);
    }

    /**
     * Checks if parent has a subdirectory. Adds a temporary child
     * if it does.
     * @param parent - the node to check.
     */
    public void lookLevelDown(DefaultMutableTreeNode parent){
        MyFileNode mfn = (MyFileNode) parent.getUserObject();
        if(mfn.isDirectory()){
            if(mfn.hasSubDirectory()){
                DefaultMutableTreeNode temp = new DefaultMutableTreeNode("Temp");
                parent.add(temp);
            }
        }
    }

    /**
     * Adds the children of the parent (only directories).
     * @param parent - the parent to add children to.
     */
    public void addChildren(DefaultMutableTreeNode parent){
        MyFileNode mfn = (MyFileNode) parent.getUserObject();
        File[] files = mfn.getFile().listFiles();
        if(parent.getChildCount() == 1 && parent.getChildAt(0).toString().equals("Temp")){ // if temp value.
            parent.remove(0);
        }
        for(int i = 0; i < files.length; i++){
            MyFileNode mfnChild = new MyFileNode(files[i].getAbsolutePath());
            if(mfnChild.isDirectory()){
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(mfnChild);
                lookLevelDown(node);
                parent.add(node);
            }
        }
    }

    class MyTreeListener implements TreeSelectionListener, TreeWillExpandListener {

        @Override
        public void treeWillExpand(TreeExpansionEvent event) {
            TreePath e = event.getPath();
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getLastPathComponent();
            addChildren(node);
        }

        @Override
        public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {
        }

        @Override
        public void valueChanged(TreeSelectionEvent e) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) dirtree.getLastSelectedPathComponent();
            MyFileNode mfn = (MyFileNode) node.getUserObject();
            nodeSelected = mfn;
            System.out.println(mfn.toString() + "; isDirectory(): " + mfn.isDirectory() + "; isSub(): " + mfn.hasSubDirectory() + "; Class: " + mfn.getClass());
        }
    }
}
