import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreePath;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    MyFileNode frameDrive;

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
        MyFileNode frameDrive = new MyFileNode(a.currentDrive);
        nodeSelected = new DefaultMutableTreeNode(frameDrive);
        this.setTitle(frameDrive.getFileName());

        left = new DirPanel(nodeSelected);
        left.dirtree.addTreeSelectionListener(new FileFrameListener());
        left.dirtree.addTreeWillExpandListener(new FileFrameListener());
        right = new FilePanel(nodeSelected);
        right.tableOfFiles.addMouseListener(new FilePanelListener());
        //right.addMouseListener(new PopListener());

        splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left, right);
        splitpane.setSize(600,400);
        right.tableOfFiles.setDragEnabled(true);
        this.setDropTarget(new MyDropTarget());
        this.addInternalFrameListener(new FileFrameListener());
        this.getContentPane().add(splitpane);
        this.setClosable(true);
        this.setMaximizable(true);
        this.setIconifiable(true);
        this.setSize(700,500);
        this.setVisible(true);
    }

    class FilePanelListener extends MouseAdapter {
        public void mouseClicked(MouseEvent e){
            if(e.getClickCount() == 2){
                int row = right.tableOfFiles.getSelectedRow();
                String name = (String) right.tableOfFiles.getModel().getValueAt(row, 0);  //returns the name of file.
                System.out.println(name);
//                String filePath = (String) right.tableOfFiles.getModel().getValueAt(row, 3);
//                System.out.println("FILE PATH: " + filePath);
                MyFileNode file = (MyFileNode) left.nodeSelected.getUserObject();
                String fileName = file.getFileName() + File.separator + name;
                System.out.println("open: " + fileName);
                File temp = new File(fileName);
                Desktop desktop = Desktop.getDesktop();
                try{
                    desktop.open(temp);
                } catch(IOException ex){
                    System.out.println(ex.toString());
                }
            }
            if(SwingUtilities.isRightMouseButton(e)){
                PopUp menu = new PopUp(a);
                menu.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    }

    class FileFrameListener implements TreeSelectionListener, TreeWillExpandListener, InternalFrameListener {

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
            frameDrive = (MyFileNode) node.getUserObject();
            left.nodeSelected = node;
            right.selectedNode = node;
            right.showFileDetails();
            MyFileNode mfn = (MyFileNode) node.getUserObject();
            frame.setTitle(mfn.getFileName());
            System.out.println(mfn.toString() + "; isDirectory(): " + mfn.isDirectory() + "; isSub(): " + mfn.hasSubDirectory() + "; Class: " + mfn.getClass());
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
            MyFileNode node = (MyFileNode) nodeSelected.getUserObject();
            File current = node.getFile();
            a.resetStatus(current);
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
                if(evt.getTransferable().isDataFlavorSupported(DataFlavor.stringFlavor)){

                    String temp = (String)evt.getTransferable().getTransferData(DataFlavor.stringFlavor);
                    String[] nextRow = temp.split("\\n");
                    String[] rowComponents;
                    for(int i = 0; i < nextRow.length; i++){
                        rowComponents = nextRow[i].split("\\t");

                        //rowComponents[3] = file Parent Path ... rowComponents[0] = file name.
                        File drag = new File(rowComponents[3] + File.separator + rowComponents[0]);
                        MyFileNode mfn = (MyFileNode) left.nodeSelected.getUserObject();
                        File dropDrive = mfn.getFile();
                        //dropDrive is the directory of where you want to drop.
                        File dropItem = new File(dropDrive.getAbsolutePath() + File.separator + drag.getName());
                        //dropItem is the new file that will happen.
                        Files.copy(drag.toPath(), dropItem.toPath());
                        //copy the contents of the dragged file to the dropped file.

                        if(drag.isDirectory()){ // if it's a directory, copy it's files also.
                            File[] files = drag.listFiles();
                            for(File f : files){
                                File source = f;
                                File tempFile = new File(dropItem.getAbsolutePath() + File.separator + source.getName());
                                Files.copy(source.toPath(), tempFile.toPath());
                            }
                        }
                        right.model.addRow(rowComponents);
                    }
                }
                else {
                    result = (List) evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                    for (Object o : result) {
                        File fileAdded = (File) o;
                        System.out.println("fileAdded: " + fileAdded.getAbsolutePath());
                        File destination = new File(fileAdded.getAbsolutePath());
                        Files.copy(fileAdded.toPath(), destination.toPath());
                        MyFileNode mfn = (MyFileNode) left.nodeSelected.getUserObject();
                        File parent = mfn.getFile();
                        File copy = new File(parent.getAbsolutePath() + "\\" + destination.getName());
                        Files.copy(destination.toPath(), copy.toPath());

                        if (fileAdded.isDirectory()) {
                            Object[] data = {fileAdded.getName(), "", ""};
                            right.model.addRow(data);
                        } else {
                            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                            String dateModified = formatter.format(fileAdded.lastModified());
                            DecimalFormat dformat = new DecimalFormat("#,###");
                            String sizeOfFile = dformat.format(fileAdded.length());
                            Object[] data = {fileAdded.getName(), dateModified, sizeOfFile};
                            right.model.addRow(data);
                        }
                    }
                }
                right.readFiles();
            }
            catch(Exception ex){
                System.out.println("Not Able To Drop");
            }
        }
    }
}
