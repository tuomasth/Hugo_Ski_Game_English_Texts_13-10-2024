package hugohiihto;

import javax.swing.*;

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
                System.out.println("Hugo Skiing " + GameDisplay.VERSION + ", GAME SPEED (ms): " + GameDisplay.GAMESPEED + ", Finnish voices");
                GameDisplay.f = new JFrame("HUGO - SKIING");
                GameDisplay.f.setIconImage(new ImageIcon("res/favicon_corner.png").getImage());
                GameDisplay.f.setSize(GameDisplay.d);
                GameDisplay.f.setMaximumSize(GameDisplay.d); // changing the dimension affects how the graphics will show up, do not edit
                GameDisplay.f.setResizable(false);
                GameDisplay.f.setLocationRelativeTo(null);
                GameDisplay.f.setVisible(true);
                GameDisplay.f.setContentPane(new GameDisplay());
                GameDisplay.f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            } catch (Exception e) {
                GameDisplay.f = new JFrame("SOME FILES ARE PROBABLY MISSING OR THEY HAVE BEEN RENAMED OR EDITED");
                GameDisplay.f.setSize(GameDisplay.d);
                GameDisplay.f.setLocationRelativeTo(null);
                GameDisplay.f.setVisible(true);
                GameDisplay.f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                System.out.println(e);
            }
        });
    }
}
