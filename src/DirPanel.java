import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.io.File;

/**
 * JPanel for the Directory Window.
 */
public class DirPanel extends JPanel{
    private JScrollPane scrollpane = new JScrollPane();
    private JTree dirtree = new JTree();

    /**
     * Adds a scrollpane and a JTree to the directory window.
     */
    public DirPanel(){
        this.setLayout(new BorderLayout());
        buildDirTree();
        dirtree.addTreeExpansionListener(new MyTreeExpansionListener());
        scrollpane.setViewportView(dirtree);
        scrollpane.setSize(this.getSize());
        this.add(scrollpane, BorderLayout.CENTER);
    }

    public void buildDirTree(){
        File drive = new File("C:\\");
        File[] files = drive.listFiles();
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(drive.getAbsolutePath());
        DefaultTreeModel treemodel = new DefaultTreeModel(root);

        for(int i = 0; i < files.length; i++){
            MyFileNode myfilenode = new MyFileNode(files[i].getAbsolutePath());
            DefaultMutableTreeNode subnode = new DefaultMutableTreeNode(myfilenode);
            if(myfilenode.isDirectory()){
                DefaultMutableTreeNode temp = new DefaultMutableTreeNode("Temp");   //Temp bc we don't want to read the whole drive yet.
                subnode.add(temp);                                                          // When we select the node, update it's children with files.
            }
            root.add(subnode);
        }
        dirtree.setModel(treemodel);
    }

//    public void addChildren(MyFileNode mfn, DefaultMutableTreeNode root){
//        File[] files = mfn.getFile().listFiles();
//        for(int i = 0; i < files.length; i++){
//            MyFileNode myfilenode = new MyFileNode(files[i].getAbsolutePath());
//            DefaultMutableTreeNode subnode = new DefaultMutableTreeNode(myfilenode);
//            if(myfilenode.isDirectory()){
//                DefaultMutableTreeNode temp = new DefaultMutableTreeNode("Temp");   //Temp bc we don't want to read the whole drive yet.
//                subnode.add(temp);                                                          // When we select the node, update it's children with files.
//            }
//            root.add(subnode);
//        }
//    }

    class MyTreeExpansionListener implements TreeExpansionListener{

        @Override
        public void treeExpanded(TreeExpansionEvent event) {
            TreePath e = event.getPath();
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getLastPathComponent();
            System.out.println("Tree has been expanded : " + node.toString());
        }

        @Override
        public void treeCollapsed(TreeExpansionEvent event) {
            System.out.println("Tree has been collapsed");
        }
    }
}
