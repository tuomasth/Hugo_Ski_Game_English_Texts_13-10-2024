package hugohiihto;

import javax.swing.*;
import java.awt.*;

public class Sprite {
    public Image image;
    public int x;
    public int y;

    public void load(String filename, int x, int y) {
        this.x = x;
        this.y = y;

        ImageIcon icon = new ImageIcon(filename);
        icon.setImage(icon.getImage().getScaledInstance(icon.getIconWidth(), icon.getIconHeight(), Image.SCALE_DEFAULT));

        image = icon.getImage();
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
