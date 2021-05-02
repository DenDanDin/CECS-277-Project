import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener for the collapse button.
 * @author Daniel De Guzman and Andy Wong
 */
public class collapseActionListener implements ActionListener {
	
    App app;
	/**
		Constructor for collapseActionListener.
		
		@param a - the app to get the selected frame.
	*/
    public collapseActionListener(App a){
        app = a;
    }

	/**
		Collapse the branch in the DirPanel.
		
		@param e - the event.
	*/
    @Override
    public void actionPerformed(ActionEvent e) {
        if(app.frame.isSelected() == true) {
            System.out.println("Collapse");
            int row = app.frame.left.dirtree.getLeadSelectionRow();
            app.frame.left.dirtree.collapseRow(row);
        }
    }
}
