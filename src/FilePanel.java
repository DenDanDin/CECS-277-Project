import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * JPanel for the File Panel Window (right side).
 * @author Daniel De Guzman and Andy Wong
 */ 
public class FilePanel extends JPanel {
    private JScrollPane scrollpane = new JScrollPane();
    JTable tableOfFiles;
    DefaultTableModel model;
    TableColumnModel columnModel;
    DefaultMutableTreeNode selectedNode;
    String[] columnNames = {"Name", "Date", "Size", "ParentPath"};
    private int columnMinWidth;
    private int columnMaxWidth;
    private int columnWidth;

    /**
     * Constructor for a FilePanel.
     * @param node - the node to display files.
     */
    public FilePanel(DefaultMutableTreeNode node){
        this.setLayout(new BorderLayout());
        this.selectedNode = node;
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
        columnModel = tableOfFiles.getColumnModel();
        columnMinWidth = columnModel.getColumn(0).getMinWidth();
        columnMaxWidth = columnModel.getColumn(0).getMaxWidth();
        columnWidth = columnModel.getColumn(0).getWidth();

        columnModel.getColumn(3).setMinWidth(0);
        columnModel.getColumn(3).setMaxWidth(0);
        columnModel.getColumn(3).setWidth(0);

        tableOfFiles.setShowGrid(false);
        tableOfFiles.setTableHeader(null);

        showFileDetails();

        scrollpane.setViewportView(tableOfFiles);
        scrollpane.setSize(this.getSize());
        this.add(scrollpane, BorderLayout.CENTER);
    }

    /**
     * Shows the full file details (Name, Date Modified, Size).
     */
    public void showFileDetails(){
        for(int i = 0; i < columnNames.length-1; i++){
            columnModel.getColumn(i).setMinWidth(columnMinWidth);
            columnModel.getColumn(i).setMaxWidth(columnMaxWidth);
            columnModel.getColumn(i).setWidth(columnWidth);
        }
        readFiles();
    }

    /**
     * Only shows the file's name.
     */
    public void showFileSimple(){
        for(int i = 1; i < columnNames.length; i++){
            columnModel.getColumn(i).setMinWidth(0);
            columnModel.getColumn(i).setMaxWidth(0);
            columnModel.getColumn(i).setWidth(0);
        }
        tableOfFiles.setModel(model);
    }

    /**
     * Reads the Files and adds them to the JTable.
     */
    public void readFiles(){
        MyFileNode mfn = (MyFileNode) selectedNode.getUserObject();
        File[] files = mfn.getFile().listFiles();
        if(files != null){
            model.setRowCount(0);
            for(int i = 0; i < files.length; i++){
                if(files[i].isDirectory()){
                    String parentPath = files[i].toPath().getParent().toString();
                    Object[] data = {files[i].getName(), "", "", parentPath};
                    model.addRow(data);
                }
                else{
                    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                    String dateModified = formatter.format(files[i].lastModified());
                    DecimalFormat dformat = new DecimalFormat("#,###");
                    String sizeOfFile = dformat.format(files[i].length());
                    String parentPath = files[i].toPath().getParent().toString();
                    Object[] data = {files[i].getName(), dateModified, sizeOfFile, parentPath};
                    model.addRow(data);
                }
            }
        }
        tableOfFiles.setModel(model);
    }
}
