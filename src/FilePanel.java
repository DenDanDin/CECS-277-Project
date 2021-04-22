import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.io.File;
/**
 * JPanel for the File Panel Window
 */ 
public class FilePanel extends JPanel {
    private JList myList = new JList();
    private JScrollPane scrollpane = new JScrollPane();
    DefaultListModel model;
    DefaultMutableTreeNode select;
    /**
     *  Adds a JList and scroll pane to the File Panel
     */
    public FilePanel(DefaultMutableTreeNode node){
        this.setLayout(new BorderLayout());
        model = new DefaultListModel();
        this.select = node;
        showFiles(select);
        scrollpane.setViewportView(myList);
        scrollpane.setSize(this.getSize());
        this.add(scrollpane, BorderLayout.CENTER);
    }
    /**
     *  Method to list files.  Iterates through files and adds to DefaultListModel for display.
     *
     * @param node - the node to get files from.
     */
    public void showFiles(DefaultMutableTreeNode node){
        MyFileNode mfn = (MyFileNode) node.getUserObject();
        File[] files = mfn.getFile().listFiles();
        if(files != null){
            model.removeAllElements();  //refresh the TreeModel.
            for(int i = 0; i < files.length; i++){
                model.addElement(files[i].getName());
            }
            myList.setModel(model);
        }
    }


}
