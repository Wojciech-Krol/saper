package cwiki14_11_2022_saper;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game {

    private static Font czcionka;
    public static Font getFont() {
        try {
            InputStream is = Game.class.getResourceAsStream("comicSans.ttf");
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            return font;
        } catch (FontFormatException | IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            return getFont();
        }
    }

    public static void main(String[] args){
        final boolean shouldFill = true;
        final boolean shouldWeightX = true;
        JFrame ramka = new JFrame();
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        JPanel panel = new JPanel();
        ImageIcon img = new ImageIcon("./src/cwiki14_11_2022_saper/icon.jpg");
        JButton rozmiarM = new JButton("20x20");
        JButton rozmiarS = new JButton("30x30");
        JButton rozmiarD = new JButton("40x40");
        panel.setLayout(layout);
        JLabel title = new JLabel();
        title.setText("-----SAPER-----");
        czcionka = getFont();
        System.out.println(czcionka.getSize());
        Font comicSans = getFont().deriveFont(45f);
        title.setFont(comicSans);


        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 40;      //make this component tall
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 1;
        c.gridy = 0;
        c.anchor= GridBagConstraints.PAGE_START;
        panel.add(title, c);

        c.anchor= GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.ipady=0;
        c.gridwidth=1;
        c.gridx = 0;
        c.gridy = 1;
        panel.add(rozmiarM, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 1;
        panel.add(rozmiarS, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 1;
        panel.add(rozmiarD, c);
        title.setText("-----SAPER-----");
        czcionka = getFont();
        System.out.println(czcionka.getSize());
        //Font comicSans = getFont().deriveFont(30f);
        //title.setFont(comicSans);
        ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ramka.setContentPane(panel);
        ramka.setSize(500,500);
        ramka.setIconImage(img.getImage());
        ramka.setVisible(true);

    }
}
