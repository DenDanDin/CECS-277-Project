import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class PopUp extends JPopupMenu{
	/*
	 * Creating the options of the pop up menu
	 */
	JMenuItem copy;
	JMenuItem paste;
	JMenuItem rename;
	JMenuItem delete;
	App app;
	public PopUp(App a) {

		app = a;

		// Creating them as JMenuItems
		copy = new JMenuItem("Copy");
		copy.addActionListener(new copyActionListener(app));
		paste = new JMenuItem("Paste");
		paste.addActionListener(new pasteActionListener(app));
		rename = new JMenuItem("Rename");
		rename.addActionListener(new renameActionListener());
		delete = new JMenuItem("Delete");
		delete.addActionListener(new deleteActionListener(app));
		// Adding to the JPopUpMenu
		add(rename);
		add(copy);
		add(paste);
		add(delete);
	}

}
