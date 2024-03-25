package cz.josefraz.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Rectangle extends Shape {

    private int lengthA, lengthB;

    public Rectangle(int positionX, int postitionY, String outlineColor, String fillColor, int lengthA, int lengthB, float strokeWidth) {
        super(positionX, postitionY, outlineColor, fillColor, strokeWidth);
        this.lengthA = lengthA;
        this.lengthB = lengthB;
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
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.decode(this.getFillColor()));
        g2d.fillRect(this.getPositionX(), this.getPostitionY(), this.getLengthA(), this.getLengthB());
        g2d.setStroke(new BasicStroke(this.getStrokeWidth()));
        g2d.setColor(Color.decode(this.getOutlineColor()));
        g2d.drawRect(this.getPositionX(), this.getPostitionY(), this.getLengthA(), this.getLengthB());
    }
}
