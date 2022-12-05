package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GUI{

	////////////////////////////////////////
	//ten plik do wywalenia, trzeba wykorzystac z niego kod po prostu
	////////////////////////////////////////

	JPanel p;
	JPanel button_p;
	GUI(int n, int m, JPanel panel){  
		
		ActionListener onKlik = new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton) e.getSource();
		        System.out.println(btn.getClientProperty("row")+" "+btn.getClientProperty("column"));
			}
		};
		
	    p = panel;
	    button_p = new JPanel();
	    
	    button_p.setLayout(new GridLayout(n,m));   
	    Icon icon = new ImageIcon("./src/lab_14_11_2022/bomba.png");
	    JButton[][] buttons = new JButton[n][m];
	    for(int i = 0; i < n; i++) {
	    	for(int j = 0; j < m; j++) {
	    		buttons[i][j] = new JButton(icon);
	    		buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 10));
	    		buttons[i][j].setFocusPainted(false);
	    		buttons[i][j].setContentAreaFilled(false);
	    		buttons[i][j].putClientProperty("column", i);
	    		buttons[i][j].putClientProperty("row", j);
	    		buttons[i][j].addActionListener(onKlik);
	    		button_p.add(buttons[i][j]);
	    		
	    	}
	    	
	    }  
	    button_p.setPreferredSize(new Dimension(20*n,20*m));
	    
	    p.add(button_p);

	    	
	    p.setSize(20*n,20*m);

	    
	    
	   
	}   
	
}
