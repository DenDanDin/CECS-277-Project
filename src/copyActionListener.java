import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class copyActionListener implements ActionListener {
    App app;
    String currentDirectory, oldName, newDirectory;
    CopyRename_DialogBox box;
    int rowSelected;
    public copyActionListener(App a){
        app = a;
    }
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

    class okayActionListener implements ActionListener{
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
//            newName = currentDirectory + File.separator + box.getToField();
//            File renameFile = new File(currentDirectory + File.separator + oldName);
//            System.out.println("Old File: " + renameFile.getAbsolutePath());
//            System.out.println("Renamed File: " + newName);
//            boolean success = renameFile.renameTo(new File(newName));
//            if(success){
//                System.out.println("File Renamed");
//                app.frame.right.readFiles();
//            }
//            else{
//                System.out.println("Not able to rename");
//            }
            box.setVisible(false);
        }
    }

    class cancelActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("You Pressed Cancel");
            box.setVisible(false);
        }
    }
}
