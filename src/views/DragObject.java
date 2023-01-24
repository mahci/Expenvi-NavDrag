package views;

import javax.swing.*;
import java.awt.*;

public class DragObject extends JLabel {

    /**
     * Create the (square) object with size, color
     * @param size Side size
     * @param color Color
     */
    public DragObject(int size, Color color) {
        super(" ");

        setOpaque(true);
        setBackground(color);
        setSize(size, size);
    }

    /**
     * Create the (square) object with size, color, and position
     * @param size Side size
     * @param color Color
     * @param x Top left x
     * @param y Top left y
     */
    public DragObject(int size, Color color, int x, int y) {
        super(" ");

        setOpaque(true);
        setBackground(color);
        setBounds(x, y, size, size);
    }

    /**
     * Set the position by the top left point
     * @param tlPosition Top-left position
     */
    public DragObject setPosition(Point tlPosition) {
        setBounds(tlPosition.x, tlPosition.y, getWidth(), getHeight());

        return this;
    }

    /**
     * Set the position by the top left point
     * @param x X position
     * @param y Y position
     */
    public DragObject setPosition(int x, int y) {
        setBounds(getX(), getY(), getWidth(), getHeight());
        return this;
    }

    public void changePosition(int dX, int dY) {
        setBounds(getX() + dX, getY() + dY, getWidth(), getHeight());
    }

}
