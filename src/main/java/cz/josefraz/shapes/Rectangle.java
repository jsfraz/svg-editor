package cz.josefraz.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Rectangle extends Shape {

    private int lengthA, lengthB;

    public Rectangle(int positionX, int postitionY, String borderColor, String fillColor, int lengthA, int lengthB,
            float strokeWidth) {
        super(positionX, postitionY, borderColor, fillColor, strokeWidth);
        this.lengthA = lengthA;
        this.lengthB = lengthB;
    }

    public Rectangle() {
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
        g2d.fillRect(getPositionX(), getPositionY(), getLengthA(), getLengthB());
        g2d.setStroke(new BasicStroke(getStrokeWidth()));
        g2d.setColor(Color.decode(getborderColor()));
        g2d.drawRect(getPositionX(), getPositionY(), getLengthA(), getLengthB());
    }

    @Override
    public void calculatePositionFromCenter(int mouseX, int mouseY) {
        // Výpočet středového bodu
        int xCenter = mouseX;
        int yCenter = mouseY;
        // Výpočet nových souřadnic obdélníku tak, aby střed ležel na (mouseX, mouseY)
        int newPositionX = xCenter - lengthA / 2;
        int newPositionY = yCenter - lengthB / 2;
        // Nastavení nových souřadnic obdélníku
        setPositionX(newPositionX);
        setPositionY(newPositionY);
    }

    @Override
    public void calculatePositionAndSizeFromStartEndPoints(Point start, Point end) {
        // TODO Auto-generated method stub
        
    }
}
