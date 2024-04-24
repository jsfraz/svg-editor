package cz.josefraz.shapes;

import java.awt.Graphics2D;
import java.awt.Point;

public interface IShape {

    public void draw(Graphics2D g2d);

    // Spočítá ze středu levou horní souřadnici
    public void calculatePositionFromCenter(int mouseX, int mouseY);

    // Spočítá levou horní souřadnici a velikost z daných dvou bodů tahu myší
    public void calculatePositionAndSizeFromStartEndPoints(Point start, Point end);

    // TODO Translate pro zobrazení "korektní" souřadnice levého horního rohu místo středu
    // public String getTransform();
}
