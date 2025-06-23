package hugohiihto;

import javax.swing.*;

import static hugohiihto.GameDisplay.GAMESPEED;
import static hugohiihto.GameDisplay.VERSION;

public class Main {

    /**
     * The main method. The program execution starts here.
     * Hugo Ski Game for Java, PC video game, designed for laptops and desktops.
     * <p>
     * Tested with Microsoft Windows 11, 64 bit
     * Java developed by Oracle / Sun Microsystems
     * Recommended Java JDK version: 23 (or 17)
     * <p>
     * Tuomas T. Hyvönen, 2024, Finland
     * Apache NetBeans 23, older versions used too when developing
     *
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> { // lambda expression, https://www.w3schools.com/java/java_lambda.asp
            try {
                System.out.println("Hugo Skiing " + VERSION + ", GAME SPEED (ms): " + GAMESPEED + ", Finnish voices");
                JFrame f = new JFrame("HUGO - SKIING");
                f.setIconImage(new ImageIcon("res/favicon_corner.png").getImage());
                f.setSize(GameDisplay.d);
                f.setMaximumSize(GameDisplay.d); // changing the dimension affects how the graphics will show up, do not edit
                f.setResizable(false);
                f.setLocationRelativeTo(null);
                f.setVisible(true);
                f.setContentPane(new GameDisplay());
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            } catch (Exception e) {
                JFrame f = new JFrame("SOME FILES ARE PROBABLY MISSING OR THEY HAVE BEEN RENAMED OR EDITED");
                f.setSize(GameDisplay.d);
                f.setLocationRelativeTo(null);
                f.setVisible(true);
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                System.out.println(e);
            }
        });
    }
}
