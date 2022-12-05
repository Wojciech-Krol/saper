package gui;

import gui.builders.GameBuilder;
import scripts.GameState;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.concurrent.TimeUnit;
import javax.swing.SwingConstants;

import static gui.MainFrameSaper.gameScene;
import static scripts.GameState.timer;

public class endScreen extends JFrame {

    public void closeWindows() {
        for (int i = 0; i < MainFrameSaper.getFrames().length; i += 1) {
            MainFrameSaper.getFrames()[i].dispose();
        }
    }
    private JPanel contentPane;
    public boolean exists=false;

    public endScreen() throws InterruptedException {
        exists=true;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 200);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 0, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Czy chcesz zagrać jeszcze raz?");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Monospaced", Font.PLAIN, 18));
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setBounds(0, 44, 390, 36);
        contentPane.add(lblNewLabel);

        JButton btnNewButton = new JButton("tak.");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                exists=false;
                MainFrameSaper.main(null);
                endScreen.super.dispose();
                closeWindows();
            }
        });
        btnNewButton.setFont(new Font("Monospaced", Font.BOLD, 14));
        btnNewButton.setBounds(63, 82, 89, 23);
        contentPane.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("nie_");
        btnNewButton_1.setFont(new Font("Monospaced", Font.BOLD, 14));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnNewButton_1.setBounds(180, 82, 89, 23);
        contentPane.add(btnNewButton_1);
    }
}
