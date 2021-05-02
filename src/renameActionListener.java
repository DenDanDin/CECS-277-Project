import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * ActionListener for the rename function.
 * @author Daniel De Guzman and Andy Wong
 */
public class renameActionListener implements ActionListener {
    App app;
    String currentDirectory, oldName, newName;
    CopyRename_DialogBox box;
    int rowSelected;

    /**
     * Constructor for the rename function.
     * @param a - the app to get information from.
     */
    public renameActionListener(App a){
        app = a;
    }

    /**
     * Displays a Rename DialogBox that will prompt the user to enter
     * the complete path + file name to rename to.
     * @param e - the event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Rename Pressed");
        box = new CopyRename_DialogBox(app, true);
        box.addOkayListener(new okayActionListener());
        box.addCancelListener(new cancelActionListener());
        box.setLocation(app.getX()+100, app.getY()+100);
        box.setBoxTitle("Rename");
        currentDirectory = app.frame.title;
        box.setCurrentDirectory(currentDirectory);
        rowSelected = app.frame.right.tableOfFiles.getSelectedRow();
        oldName = (String) app.frame.right.tableOfFiles.getModel().getValueAt(rowSelected, 1);
        box.setFromField(oldName);
        box.setVisible(true);
    }

    /**
     * ActionListener for Ok button.
     * @author Daniel De Guzman and Andy Wong
     */
    class okayActionListener implements ActionListener{

        /**
         * Renames the selected file to the user's path in To: field.
         * @param e - the event.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Ok Pressed");
            newName = box.getToField();
            File renameFile = new File(currentDirectory + File.separator + oldName);
            boolean success = renameFile.renameTo(new File(newName));
            if(success){
                System.out.println("File Renamed to: " + renameFile.getAbsolutePath());
                app.frame.right.readFiles();
            }
            else{
                System.out.println("Not able to rename");
            }
            box.setVisible(false);
        }
    }

    /**
     * ActionListener for the Cancel button.
     * @author Daniel De Guzman and Andy Wong
     */
    class cancelActionListener implements ActionListener{
        /**
         * Exits the Dialog Box.
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Cancel Pressed");
            box.setVisible(false);
        }
    }
}
