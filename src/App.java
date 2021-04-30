import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;


public class App extends JFrame{
    JPanel panel, topPanel;
    JMenuBar menubar;
    JToolBar toolbar, drivebar, statusbar;
    JDesktopPane desktop;
    ArrayList<FileFrame> list_ff;
    FileFrame frame;
    JButton simple, details;
    String currentDrive;
    JMenuItem expand_branch, collapse_branch;
    JComboBox combo;
    JLabel statusLabel;
//    MouseListener poplisten;
    File copyFile;
    private File[] drives;

    /**
     * Initializes the panels and features of File Manager
     */
    public App(){
        panel = new JPanel();
        topPanel = new JPanel();
        menubar = new JMenuBar();
        toolbar = new JToolBar();
        drivebar = new JToolBar();
        statusbar = new JToolBar();
        desktop = new JDesktopPane();
        list_ff = new ArrayList<FileFrame>();

        simple = new JButton("Simple");
        details = new JButton("Details");
        drives = File.listRoots();
        combo = new JComboBox(drives);
        currentDrive = drives[0].getAbsolutePath();
        statusLabel = new JLabel();
        frame = new FileFrame(this);
//        poplisten = new PopListener();
    }

    /**
     * Makes all panels visible, builds menus, etc.
     */
    public void go(){
        this.setTitle("CECS 277 File Manager");
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);
        topPanel.setLayout(new BorderLayout());
        buildMenu();
        topPanel.add(menubar, BorderLayout.NORTH);
        topPanel.setBackground(Color.BLUE);
        panel.add(topPanel, BorderLayout.NORTH);
        
        // Set window to be able to right click
        // **IMPORTANT**
        // Replace this with whatever variable the actual file explorer window is called, wasn't too sure so temporarily
        // leaving as this
        //this.addMouseListener(poplisten);


        frame.setLocation(desktop.getX(), desktop.getY()+100);
        list_ff.add(frame);
        desktop.add(list_ff.get(0));

        buildToolbar();
        topPanel.add(toolbar, BorderLayout.SOUTH);
        buildStatusBar();
        panel.add(statusbar, BorderLayout.SOUTH);
        panel.add(desktop, BorderLayout.CENTER);

        File file = (File) combo.getSelectedItem();
        currentDrive = file.getAbsolutePath();

        addActionListeners();
        this.add(panel);
        this.setSize(1000, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * Builds the Menu (File, Tree, Window, Help)
     */
    public void buildMenu(){
        buildFileMenu();
        buildTreeMenu();
        buildWindowMenu();
        buildHelpMenu();
    }

    /**
     * Builds the File part of the Menu.
     */
    public void buildFileMenu(){
        JMenu file = new JMenu("File");
        JMenuItem rename, copy, delete, run, exit;
        rename = new JMenuItem("Rename");
        copy = new JMenuItem("Copy");
        delete = new JMenuItem("Delete");
        run = new JMenuItem("Run");
        run.addActionListener(new runActionListener(frame));
        exit = new JMenuItem("Exit");
        exit.addActionListener(new exitActionListener());
        file.add(rename);
        file.add(copy);
        file.add(delete);
        file.add(run);
        file.add(exit);
        menubar.add(file);
    }

    
    /**
     * Builds the Tree part of the Menu.
     */
    public void buildTreeMenu(){
        JMenu tree = new JMenu("Tree");
        expand_branch = new JMenuItem("Expand Branch");
        collapse_branch = new JMenuItem("Collapse Branch");
        tree.add(expand_branch);
        tree.add(collapse_branch);
        menubar.add(tree);
    }

    /**
     * Builds the Window part of the Menu.
     */
    public void buildWindowMenu(){
        JMenu window = new JMenu("Window");
        JMenuItem item_new, cascade;
        item_new = new JMenuItem("New");
        item_new.addActionListener(new newActionListener(this));
        cascade = new JMenuItem("Cascade");
        cascade.addActionListener(new cascadeActionListener(this));
        window.add(item_new);
        window.add(cascade);
        menubar.add(window);
    }

    /**
     * Builds the Help part of the Menu.
     */
    public void buildHelpMenu(){
        JMenu help = new JMenu("Help");
        JMenuItem item_help, about;
        item_help = new JMenuItem("Help");
        item_help.addActionListener(new helpActionListener());
        about = new JMenuItem("About");
        about.addActionListener(new aboutActionListener());
        help.add(item_help);
        help.add(about);
        menubar.add(help);
    }

    /**
     * Builds the Toolbar (Combobox, Details, Simple)
     */
    public void buildToolbar(){
        toolbar.setLayout(new FlowLayout());
        toolbar.setFloatable(false);
        ActionListener comboActionListener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = (File) combo.getSelectedItem();
                currentDrive = file.getAbsolutePath();
                System.out.println("ACTION: " + currentDrive);
            }
        };
        combo.addActionListener(comboActionListener);
        toolbar.add(combo);

        Dimension dim = new Dimension(120,30);
        details.setPreferredSize(dim);
        simple.setPreferredSize(dim);

        toolbar.add(details);
        toolbar.add(simple);
    }

    /**
     * Builds the Statusbar (Current Drive, Free Space,
     *  Used Space, Total Space)
     */
    public void buildStatusBar(){
        statusbar.setLayout(new BorderLayout());
        File disk = (File) combo.getSelectedItem();
        resetStatus(disk);
        statusbar.add(statusLabel);
    }

    public void resetStatus(File drive){
        String cd, fspace, uspace, tspace;
        currentDrive = drive.getAbsolutePath();

        cd = "Current Drive: " + currentDrive;
        System.out.println(cd);
        long totalUsed = drive.getTotalSpace() - drive.getFreeSpace();
        fspace = "  Free Space: " + (drive.getFreeSpace()/1000000000) + "GB";
        uspace = "  Used Space: " + (totalUsed/1000000000) + "GB";
        tspace = "  Total Space: " + (drive.getTotalSpace()/1000000000) + "GB";
        String statusOut = cd + fspace + uspace + tspace;
        statusLabel.setText(statusOut);
    }

    public void addActionListeners(){
        simple.addActionListener(new simpleActionListener(frame));
        details.addActionListener(new detailsActionListener(frame));
        expand_branch.addActionListener(new expandActionListener(frame));
        collapse_branch.addActionListener(new collapseActionListener(frame));
    }
}