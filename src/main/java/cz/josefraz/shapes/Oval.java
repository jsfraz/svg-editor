package cz.josefraz.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Oval extends Shape {

    private int lengthA, lengthB;

    public Oval(int positionX, int postitionY, String borderColor, String fillColor, int lengthA, int lengthB,
            float strokeWidth) {
        super(positionX, postitionY, borderColor, fillColor, strokeWidth);
        this.lengthA = lengthA;
        this.lengthB = lengthB;
    }

    public Oval() {
        super(0, 0, "", "", 0);
        this.lengthA = 0;
        this.lengthB = 0;
    }

    public int getLengthA() {
        return lengthA;
    }

    public int getLengthB() {
        return lengthB;
    }

    public void setLengthA(int lengthA) {
        this.lengthA = lengthA;
    }

    public void setLengthB(int lengthB) {
        this.lengthB = lengthB;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.decode(getFillColor()));
        g2d.fillOval(getPositionX(), getPositionY(), getLengthA(), getLengthB());
        g2d.setStroke(new BasicStroke(getStrokeWidth()));
        g2d.setColor(Color.decode(getborderColor()));
        g2d.drawOval(getPositionX(), getPositionY(), getLengthA(), getLengthB());
    }

    @Override
    public void calculatePositionFromCenter(int mouseX, int mouseY) {
        // Výpočet středového bodu
        int xCenter = mouseX;
        int yCenter = mouseY;
        // Výpočet nových souřadnic oválu tak, aby střed ležel na (mouseX, mouseY)
        int newPositionX = xCenter - lengthA / 2;
        int newPositionY = yCenter - lengthB / 2;
        // Nastavení nových souřadnic oválu
        setPositionX(newPositionX);
        setPositionY(newPositionY);
    }

    @Override
    public void calculatePositionAndSizeFromStartEndPoints(Point start, Point end) {
        // Vypočítání šířky a výšky oválu
        setLengthA(Math.abs(end.x - start.x));
        setLengthB(Math.abs(end.y - start.y));
        // Vypočítání levého horního rohu oválu
        setPositionX(Math.min(start.x, end.x));
        setPositionY(Math.min(start.y, end.y));
    }
}
