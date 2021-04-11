import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;


public class App extends JFrame{
    JPanel panel, topPanel;
    JMenuBar menubar;
    JToolBar toolbar, drivebar, statusbar;
    JDesktopPane desktopPane;
    Desktop desktop;
    File[] files;
    ArrayList<FileFrame> list_ff;
    JButton simple, details;
    String currentDrive;

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
        desktopPane = new JDesktopPane();
        list_ff = new ArrayList<FileFrame>();

        simple = new JButton("Simple");
        simple.addActionListener(new simpleActionListener());

        details = new JButton("Details");
        details.addActionListener(new detailsActionListener());

        files = (new File("C:\\")).listFiles();
        desktop = Desktop.getDesktop();
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


        buildToolbar();
        topPanel.add(toolbar, BorderLayout.SOUTH);
        buildStatusbar();
        panel.add(statusbar, BorderLayout.SOUTH);

        panel.add(desktopPane, BorderLayout.CENTER);
        currentDrive = "C:";
        FileFrame ff = new FileFrame(this);
        ff.setTitle(currentDrive);
        ff.setLocation(desktopPane.getX(), desktopPane.getY()+100);
        list_ff.add(ff);
        desktopPane.add(list_ff.get(0));

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
        JMenuItem expand_branch, collapse_branch;
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
        String[] a = new String[]{"hello", "my", "name"};
        JComboBox combo = new JComboBox(a);
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
    public void buildStatusbar(){
        statusbar.setLayout(new BorderLayout());
        String cd, fspace, uspace, tspace;
        cd = "Current Drive: " + currentDrive;
        fspace = " Free Space: " + 12345 + "GB";
        uspace = " Used Space: " + 12345 + "GB";
        tspace = " Total Space: " + 12345 + "GB";
        String statusOut = cd + fspace + uspace + tspace;
        JLabel statusLabel = new JLabel(statusOut);
        statusbar.add(statusLabel);
    }

}
