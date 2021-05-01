import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * PopUp Menu. Appears when user right clicks in the File Panel.
 * @author Daniel De Guzman and Andy Wong
 */
public class PopUp extends JPopupMenu{
	/*
	 * Creating the options of the pop up menu
	 */
	JMenuItem copy;
	JMenuItem paste;
	JMenuItem rename;
	JMenuItem delete;
	App app;

	/**
	 * Constructor for the PopUp Menu.
	 * @param a - the app to get information from.
	 */
	public PopUp(App a) {

		app = a;
		// Creating them as JMenuItems
		copy = new JMenuItem("Copy");
		copy.addActionListener(new copyPopUpActionListener(app));

		paste = new JMenuItem("Paste");
		paste.addActionListener(new pasteActionListener(app));

		rename = new JMenuItem("Rename");
		rename.addActionListener(new renameActionListener(app));

		delete = new JMenuItem("Delete");
		delete.addActionListener(new deleteActionListener(app));

		// Adding to the JPopUpMenu
		add(rename);
		add(copy);
		add(paste);
		add(delete);
	}

}
