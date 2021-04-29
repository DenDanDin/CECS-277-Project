import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PopListener extends MouseAdapter {
	// Detects for mouse press, if so will run popNow which shows the menu
	public void mousePressed(MouseEvent e) {
		if(e.isPopupTrigger()) {
			popNow(e);
		}
	}
	public void mouseReleased(MouseEvent e) {
		if (e.isPopupTrigger()) {
			popNow(e);
		}
	}
	// Method to open up PopUpMenu
	public void popNow(MouseEvent e) {
		PopUp menu = new PopUp();
		menu.show(e.getComponent(), e.getX(), e.getY());
	}
}
