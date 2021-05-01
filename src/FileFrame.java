import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;


/**
 * JInternalFrame for the Left and Right Side of the Directory Window.
 * @author Daniel De Guzman and Andy Wong
 */
public class FileFrame extends JInternalFrame {

    JSplitPane splitpane;
    DirPanel left;
    FilePanel right;
    DefaultMutableTreeNode nodeSelected;
    JInternalFrame frame;
    App a;
    MyFileNode frameDrive;
    PopUp menu;
    String title;

    /**
     * Creates a splitplane where the left side will be
     * the Directory Panel, and the right side will be the File Panel.
     * @param a - the app to get information from.
     */
    public FileFrame(App a){
        this.a = a;
        menu = new PopUp(a);
        frame = this;
        title = a.currentDrive;
        this.setLayout(new BorderLayout());
        MyFileNode frameDrive = new MyFileNode(a.currentDrive);
        nodeSelected = new DefaultMutableTreeNode(frameDrive);
        this.setTitle(frameDrive.getFileName());

        left = new DirPanel(nodeSelected);
        left.dirtree.addTreeSelectionListener(new FileFrameListener());
        left.dirtree.addTreeWillExpandListener(new FileFrameListener());
        right = new FilePanel(nodeSelected);
        right.tableOfFiles.addMouseListener(new FilePanelListener());

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

    /**
     * MouseListener for the File Panel.
     * @author Daniel De Guzman and Andy Wong
     */
    class FilePanelListener extends MouseAdapter {
        /**
         * Determines if the mouse was double clicked to open a file,
         * or if the mouse was right clicked to show the popUp menu.
         * @param e - the mouse event.
         */
        @Override
        public void mouseClicked(MouseEvent e){
            if(e.getClickCount() == 2){
                int row = right.tableOfFiles.getSelectedRow();
                String name = (String) right.tableOfFiles.getModel().getValueAt(row, 0);  //returns the name of file.
                String fileName = title + File.separator + name;
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
                menu.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    }

    /**
     * Listeners for the FileFrame to tell if node has been selected, or a tree will expand.
     * @author Daniel De Guzman and Andy Wong
     */
    class FileFrameListener implements TreeSelectionListener, TreeWillExpandListener, InternalFrameListener {

        /**
         * Add the current directory's children if the tree will expand.
         * @param event - the event.
         */
        @Override
        public void treeWillExpand(TreeExpansionEvent event) {
            TreePath e = event.getPath();
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getLastPathComponent();
            left.addChildren(node);
        }

        @Override
        public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {
        }

        /**
         * Update variables for when a node in the tree (left side) is seleceted.
         * @param e - the event.
         */
        @Override
        public void valueChanged(TreeSelectionEvent e) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) left.dirtree.getLastSelectedPathComponent();
            frameDrive = (MyFileNode) node.getUserObject();
            left.nodeSelected = node;
            right.selectedNode = node;
            right.showFileDetails();
            MyFileNode mfn = (MyFileNode) node.getUserObject();
            title = mfn.getFileName();
            a.pasteDir = mfn.getFileName();
            frame.setTitle(title);
            System.out.println(mfn.toString() + "; isDirectory(): " + mfn.isDirectory() + "; isSub(): " + mfn.hasSubDirectory() + "; Class: " + mfn.getClass());
        }


        @Override
        public void internalFrameOpened(InternalFrameEvent e) {
        }

        @Override
        public void internalFrameClosing(InternalFrameEvent e) {
        }

        /**
         * Remove the JInternalFrame from the App's array list.
         * @param e - the event.
         */
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

        /**
         * Change app's focus frame when another frame is activated.
         * Updates the statusbar of the App.
         * @param e - the event.
         */
        @Override
        public void internalFrameActivated(InternalFrameEvent e) {
            System.out.println("Activation");
            a.frame = FileFrame.this;
            System.out.println(a.frame.title);
            MyFileNode node = (MyFileNode) nodeSelected.getUserObject();
            File current = node.getFile();
            a.resetStatus(current);
        }

        @Override
        public void internalFrameDeactivated(InternalFrameEvent e) {
        }
    }

    /**
     * DropTarget class to handle Drag and Drop.
     * @author Daniel De Guzman and Andy Wong
     */
    class MyDropTarget extends DropTarget {
        /**
         * Allow and process drag and drop from External Desktop and
         * between other FileFrames.
         * @param evt - the event.
         */
        public void drop(DropTargetDropEvent evt){
            try{
                evt.acceptDrop(DnDConstants.ACTION_COPY);
                List<Object> result;
                //if DnD is between FileFrames.
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
                //DnD is from an external source (a desktop).
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
