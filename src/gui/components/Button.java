package gui.components;
import javax.swing.JButton;

public class Button extends JButton {
	
	private JButton _instance;
	
	public Button(String label) {
		_instance = new JButton(label);
	}
}
