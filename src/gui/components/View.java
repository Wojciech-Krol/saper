package gui.components;

import javax.swing.JPanel;
import java.awt.GridLayout;

public class View extends JPanel {
	
	private JPanel _instance;
	
	public View(GridLayout layout) {
		_instance = new JPanel();
		
		_instance.setLayout(layout);
	}
}
