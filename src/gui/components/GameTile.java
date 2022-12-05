package gui.components;
import javax.swing.JButton;
import java.awt.*;

public class GameTile extends JButton {
	
	public boolean hasBomb;
	public boolean isPressed;
	
	public GameTile() {
		super();
		this.hasBomb = false;
		this.setPressed(false);
	    this.setFont(new Font("Arial", Font.PLAIN, 10));
	    this.setFocusPainted(false);
	    this.setContentAreaFilled(false);
	}
	
	public void showUp() {
		// change icon to empty 
	}

	public void setPressed(boolean isPressed){
		this.isPressed = isPressed;
	}

	public void setBomb(){
		this.hasBomb = true;
	}

	public boolean ifHasBomb(){
		if(this.hasBomb){
			return true;
		}else return false;
	}
	public boolean ifFlagged(){
		if(this.getText() == "F"){
			return true;
		}else return false;
	}


}
