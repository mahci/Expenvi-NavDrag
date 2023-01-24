package views;

import tools.Utils;
import java.awt.*;

public class MoRectangle extends Rectangle {

    public Point topLeft, topRight, bottomLeft, bottomRight;
    public Point center;
    public int minX, maxX, minY, maxY;

    public MoRectangle() {

    }

    public MoRectangle(int width, int height) {
        this(0, 0, width, height);
    }

    public MoRectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        updateSpecs();
    }

    public MoRectangle(Point topLeft, int w, int h) {
        this.x = topLeft.x;
        this.y = topLeft.y;
        this.width = w;
        this.height = h;

        updateSpecs();
    }

    public MoRectangle(int cx, int cy, int margin) {
        this.x = cx - margin;
        this.y = cy - margin;
        this.width = 2 * margin;
        this.height = 2 * margin;

        updateSpecs();
    }

    public MoRectangle(Point center, int margin) {
        this.x = center.x - margin;
        this.y = center.y - margin;
        this.width = 2 * margin;
        this.height = 2 * margin;

        updateSpecs();
    }

    public void setLocationTopLeft(Point topLeft) {
        x = topLeft.x;
        y = topLeft.y;

        updateSpecs();
    }

    public void setLocationTopRight(Point topRight) {
        x = topRight.x - width;
        y = topRight.y;

        updateSpecs();
    }

    public void setLocationBottomLeft(Point bottomLeft) {
        x = bottomLeft.x;
        y = bottomLeft.y - height;

        updateSpecs();
    }

    public void setLocationBottomRight(Point bottomRight) {
        x = bottomRight.x;
        y = bottomRight.y - height;

        updateSpecs();
    }

    @Override
    public void setLocation(Point p) {
        super.setLocation(p);
        updateSpecs();
    }

    @Override
    public void setLocation(int x, int y) {
        super.setLocation(x, y);
        updateSpecs();
    }

    @Override
    public void translate(int dx, int dy) {
        super.translate(dx, dy);
        updateSpecs();
    }

    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);
        updateSpecs();
    }

    /**
     * Set the height (location DOES NOT change)
     * @param height New height
     */
    public void setHeight(int height) {
        this.height = height;
        updateSpecs();
    }

    /**
     * Set the width (location DOES NOT change)
     * @param width New width
     */
    public void setWidth(int width) {
        this.width = width;
        updateSpecs();
    }


    public int getArea() {
        return height * width;
    }

    /**
     * Fit a rectangle inside this
     * @param rect Rectangle
     * @return Found position or null if not found
     */
    public Point fitRect(Rectangle rect) {
        if (rect.height > height || rect.width > width) return null;
        else {
            return new Point(
                    Utils.randInt(x, width - rect.width),
                    Utils.randInt(y, height - rect.height));
        }
    }

    public MoRectangle getMarginedRectangel(int margin) {
        return new MoRectangle(
                x - margin, y - margin,
                width + 2 * margin, height + 2 * margin);
    }

    public String printCorners() {
        String resSB = "[" + topLeft.x + "," + topLeft.y + "]" +
                "[" + topRight.x + "," + topRight.y + "]" +
                "[" + bottomRight.x + "," + bottomRight.y + "]" +
                "[" + bottomLeft.x + "," + bottomLeft.y + "]";
        return resSB;
    }

    /**
     * Get the top-lect location for centering another rectangle in this
     * @param inRect MoRectangle to put inside
     * @return Point
     */
    public Point getCenterForRect(MoRectangle inRect) {
        return new Point((width - inRect.width) / 2, (height - inRect.height) / 2);
    }

    /**
     * Set the corners based on the (x,y) and W/H
     */
    private void updateSpecs() {
        topLeft = new Point(x, y);
        topRight = new Point(x + width, y);
        bottomLeft = new Point(x, y + height);
        bottomRight = new Point(x + width, y + height);

        center = Utils.point(getCenterX(), getCenterY());

        minX = topLeft.x;
        maxX = topRight.x;
        minY = topLeft.y;
        maxY = bottomLeft.y;
    }

    @Override
    public String toString() {
        return "MoRect[" +
                "x=" + x + "," +
                "y=" + y + "," +
                "width=" + width + "," +
                "height=" + height + "]";
    }
}
