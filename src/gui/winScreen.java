package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static scripts.GameState.timer;

public class winScreen extends JFrame {

    public void closeWindows() {
        for (int i = 0; i < MainFrameSaper.getFrames().length; i += 1) {
            MainFrameSaper.getFrames()[i].dispose();
        }
    }
    private JPanel contentPane;
    public boolean exists=false;

    public winScreen() throws InterruptedException {
        exists=true;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 200);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("WYGRAŁEŚ! Czy grasz jeszcze raz?");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
        lblNewLabel.setFont(new Font("Monospaced", Font.PLAIN, 18));
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setBounds(0, 44, 430, 72);
        contentPane.add(lblNewLabel);


        JButton btnNewButton = new JButton("tak.");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exists=false;
                MainFrameSaper.main(null);
                winScreen.super.dispose();
                closeWindows();
            }
        });
        btnNewButton.setFont(new Font("Monospaced", Font.BOLD, 13));
        btnNewButton.setBounds(63, 82, 89, 23);
        contentPane.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("nie_");
        btnNewButton_1.setFont(new Font("Monospaced", Font.BOLD, 13));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnNewButton_1.setBounds(180, 82, 89, 23);
        contentPane.add(btnNewButton_1);
    }
}
