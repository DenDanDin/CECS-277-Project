import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * JPanel for the File Panel Window
 */ 
public class FilePanel extends JPanel {
    //JList myList = new JList();
    private JScrollPane scrollpane = new JScrollPane();
    JTable tableOfFiles;
    DefaultTableModel model;
    DefaultMutableTreeNode select;
    String[] columnNames = {"Name", "Date", "Size"};
    /**
     *  Adds a JList and scroll pane to the File Panel
     */
    public FilePanel(DefaultMutableTreeNode node){
        this.setLayout(new BorderLayout());
        this.select = node;
        model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        tableOfFiles = new JTable(model);
        for(String name : columnNames){
            model.addColumn(name);
        }
        tableOfFiles.setShowGrid(false);
        tableOfFiles.setTableHeader(null);
        showFiles(select);


        scrollpane.setViewportView(tableOfFiles);
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
        System.out.println("From showFiles..." + mfn.getFileName());
        File[] files = mfn.getFile().listFiles();
        if(files != null){
            model.setRowCount(0);
            for(int i = 0; i < files.length; i++){
                if(files[i].isDirectory()){
                    Object[] data = {files[i].getName(), "", ""};
                    model.addRow(data);
                }
                else{
                    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                    String dateModified = formatter.format(files[i].lastModified());
                    DecimalFormat dformat = new DecimalFormat("#,###");
                    String sizeOfFile = dformat.format(files[i].length());
                    Object[] data = {files[i].getName(), dateModified, sizeOfFile};
                    model.addRow(data);
                }
            }
        }
        tableOfFiles.setModel(model);
    }
}
