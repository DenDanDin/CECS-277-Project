import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.*;
import java.awt.*;
import java.io.File;

/**
 * JPanel for the Directory Window.
 * @author Daniel De Guzman and Andy Wong
 */
public class DirPanel extends JPanel{
    JScrollPane scrollpane = new JScrollPane();
    JTree dirtree = new JTree();
    DefaultMutableTreeNode nodeSelected;
    DefaultMutableTreeNode root;

    /**
     * Adds a scrollpane and a JTree to the directory window.
     * @param root - the current drive.
     */
    public DirPanel(DefaultMutableTreeNode root){
        this.setLayout(new BorderLayout());
        this.root = root;
        buildDirTree(root);
        scrollpane.setViewportView(dirtree);
        scrollpane.setSize(this.getSize());
        this.add(scrollpane, BorderLayout.CENTER);
    }

    /**
     * Builds the Directory Tree.
     * @param node - the root/current drive.
     */
    public void buildDirTree(DefaultMutableTreeNode node){
        nodeSelected = node;
        MyFileNode drive = (MyFileNode) node.getUserObject();
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
        nodeSelected = parent;
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
        nodeSelected = parent;
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
}
