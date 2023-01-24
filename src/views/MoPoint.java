package views;

import java.awt.*;

public class MoPoint extends Point {

    /**
     * Constructor from Point
     * @param p Point
     */
    public MoPoint(Point p) {
        super(p.x, p.y);
    }

    public MoPoint() {
        super();
    }

    public MoPoint(int x, int y) {
        super(x, y);
    }

    public MoPoint trans(int dX, int dY) {
        return new MoPoint(x + dX, y + dY);
    }

    public MoPoint trans(Point p) {
        return new MoPoint(x + p.x, y + p.y);
    }
}
