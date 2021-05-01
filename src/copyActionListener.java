import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * ActionListener for the copy function in the file menu.
 * @author Daniel De Guzman and Andy Wong
 */
public class copyActionListener implements ActionListener {
    App app;
    String currentDirectory, oldName, newDirectory;
    CopyRename_DialogBox box;
    int rowSelected;

    /**
     * Constructor for a copyActionListener.
     * (App is used to access selected nodes).
     * @param a - the app.
     */
    public copyActionListener(App a){
        app = a;
    }

    /**
     * Displays a CopyRename_DialogBox that prompts the user to input
     * the path + file name of where to copy.
     * @param e - the event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Copy");
        box = new CopyRename_DialogBox(app, true);
        box.addOkayListener(new okayActionListener());
        box.addCancelListener(new cancelActionListener());
        box.setLocation(app.getX()+100, app.getY()+100);
        box.setBoxTitle("Copying");
        currentDirectory = app.frame.title;
        box.setCurrentDirectory(currentDirectory);
        rowSelected = app.frame.right.tableOfFiles.getSelectedRow();
        oldName = (String) app.frame.right.tableOfFiles.getModel().getValueAt(rowSelected, 0);
        box.setFromField(oldName);
        box.setVisible(true);
    }

    /**
     * ActionListener for "Ok" button in CopyRename_DialogBox.
     * @author Daniel De Guzman and Andy Wong
     */
    class okayActionListener implements ActionListener{
        /**
         * Copies the file to the given path+file name the user
         * inputs in the To: box.
         * Exits DialogBox after attempted copy.
         * @param e - the event.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("You Pressed Okay");
            newDirectory = box.getToField();
            System.out.println("To: " + newDirectory);
            File copyFile = new File(currentDirectory + File.separator + oldName);
            File newFile = new File(newDirectory);
            try {
                Files.copy(copyFile.toPath(), newFile.toPath());
            } catch (IOException ioException) {
                System.out.println("Not able to copy");
            }
            box.setVisible(false);
        }
    }

    /**
     * ActionListener for "Cancel" button in CopyRename_DialogBox.
     * @author Daniel De Guzman and Andy Wong
     */
    class cancelActionListener implements ActionListener{

        /**
         * Sets DialogBox not visible.
         * @param e - the event.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("You Pressed Cancel");
            box.setVisible(false);
        }
    }
}
