package cz.josefraz.shapes;

import java.awt.Graphics2D;
import java.awt.Point;

public interface IShape {

    public void draw(Graphics2D g2d);

    public void calculatePositionFromCenter(int mouseX, int mouseY);

    public void calculatePositionAndSizeFromStartEndPoints(Point start, Point end);
}
