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
	public PopUp() {
		// Creating them as JMenuItems
		copy = new JMenuItem("Copy");
		paste = new JMenuItem("Paste");
		rename = new JMenuItem("Rename");
		delete = new JMenuItem("Delete");
		// Adding to the JPopUpMenu
		add(rename);
		add(copy);
		add(paste);
		add(delete);

	}

}
