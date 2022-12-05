package gui;

import gui.components.*;
import gui.builders.*;
import scripts.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import javax.swing.border.EmptyBorder;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Component;


//wygenerowany kod WindowBuilder


public class MainFrameSaper extends JFrame {

	private JPanel contentPane;
	
	public static JPanel menuScene;
	public static JPanel gameScene;
	
	private void startGame(int size, int bombs) {
		menuScene.setVisible(false);
		GameState gameBoard = (new GameBuilder()).getGame(size, gameScene,bombs);
		gameScene.setVisible(true);
	}
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrameSaper frame = new MainFrameSaper();
					frame.setBackground(new Color(24, 22, 22));
					frame.getContentPane().setBackground(new Color(24, 22, 22));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrameSaper() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(10, 11, 720, 720);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));

		// create scenes
		menuScene = new View(new GridLayout(9, 9, 0, 0));
		menuScene.setBackground(new Color(24, 22, 22));
		gameScene = new View(new GridLayout(1, 0, 0, 0));
		
		layeredPane.add(menuScene, "name_260824190894000");
		layeredPane.add(gameScene, "name_260902498564200");
		
		// create select game button
		Button buttonS15 = new Button("15x15");
		buttonS15.setForeground(new Color(255, 255, 255));
		buttonS15.setBorderPainted(false);
		buttonS15.setBackground(new Color(32, 32, 32));
		buttonS15.setFont(new Font("Monospaced", Font.PLAIN, 18));
		buttonS15.setText("15x15");
		buttonS15.setBounds(163, 277, 100, 100);
		buttonS15.addActionListener(actionEvent -> startGame(15, 36));
		buttonS15.addActionListener(actionEvent -> setBounds(100, 100, 754, 780));
		buttonS15.addActionListener(actionEvent -> layeredPane.setBounds(10, 11, 720, 720));

		Button buttonS25 = new Button("25x25");
		buttonS25.setForeground(new Color(255, 255, 255));
		buttonS25.setBorderPainted(false);
		buttonS25.setBackground(new Color(32, 32, 32));
		buttonS25.setFont(new Font("Monospaced", Font.PLAIN, 18));
		buttonS25.setText("25x25");
		buttonS25.setBounds(293, 277, 100, 100);
		buttonS25.addActionListener(actionEvent -> startGame(25,80));
		buttonS25.addActionListener(actionEvent -> setBounds(100, 100, 754, 780));
		buttonS25.addActionListener(actionEvent -> layeredPane.setBounds(10, 11, 720, 720));

		Button buttonS35 = new Button("35x35");
		buttonS35.setForeground(new Color(255, 255, 255));
		buttonS35.setBackground(new Color(32, 32, 32));
		buttonS35.setBorderPainted(false);
		buttonS35.setFont(new Font("Monospaced", Font.PLAIN, 18));
		buttonS35.setText("35x35");
		buttonS35.setBounds(421, 277, 100, 100);
		buttonS35.addActionListener(actionEvent -> startGame(35,150));
		buttonS35.addActionListener(actionEvent -> setBounds(100, 100, 754, 780));
		buttonS35.addActionListener(actionEvent -> layeredPane.setBounds(10, 11, 720, 720));
	
		// assign elems to scenes
		menuScene.add(buttonS15);
		menuScene.add(buttonS25);
		menuScene.add(buttonS35);

		JLabel lblNewLabel = new JLabel("saper_");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Monospaced", Font.BOLD, 55));
		lblNewLabel.setBounds(10, 166, 671, 93);
		menuScene.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("By: Natalia Malinowska, Wojciech Król, Damian Kwolek");
		lblNewLabel_1.setFont(new Font("Monospaced", Font.PLAIN, 11));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(10, 476, 474, 14);
		menuScene.add(lblNewLabel_1);
	}
}
