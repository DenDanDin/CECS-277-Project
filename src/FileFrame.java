import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import javax.swing.event.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.image.ReplicateScaleFilter;
import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

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
        this.setDropTarget(new MyDropTarget());
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

    class MyDropTarget extends DropTarget {
        public void drop(DropTargetDropEvent evt){
            try{
                evt.acceptDrop(DnDConstants.ACTION_COPY);
                List<Object> result;
                result = (List) evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                for(Object o: result){
                    File fileAdded = (File) o;
                    File destination = new File(fileAdded.getAbsolutePath());
                    Files.copy(fileAdded.toPath(), destination.toPath());
                    MyFileNode mfn = (MyFileNode) left.nodeSelected.getUserObject();
                    File parent = mfn.getFile();
                    File copy = new File(parent.getAbsolutePath() + "\\" + destination.getName());
                    Files.copy(destination.toPath(), copy.toPath());

                    if(fileAdded.isDirectory()){
                        Object[] data = {fileAdded.getName(), "", ""};
                        right.model.addRow(data);
                    }
                    else{
                        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                        String dateModified = formatter.format(fileAdded.lastModified());
                        DecimalFormat dformat = new DecimalFormat("#,###");
                        String sizeOfFile = dformat.format(fileAdded.length());
                        Object[] data = {fileAdded.getName(), dateModified, sizeOfFile};
                        right.model.addRow(data);
                    }
                }
            }
            catch(Exception ex){
                System.out.println("Not Able To Drop");
            }
        }
    }
}
