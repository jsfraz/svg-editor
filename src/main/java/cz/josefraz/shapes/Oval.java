package cz.josefraz.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Oval extends Shape {

    private int lengthA, lengthB;

    public Oval(int positionX, int postitionY, String borderColor, String fillColor, int lengthA, int lengthB, float strokeWidth) {
        super(positionX, postitionY, borderColor, fillColor, strokeWidth);
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
        g2d.fillOval(this.getPositionX(), this.getPostitionY(), this.getLengthA(), this.getLengthB());
        g2d.setStroke(new BasicStroke(this.getStrokeWidth()));
        g2d.setColor(Color.decode(this.getborderColor()));
        g2d.drawOval(this.getPositionX(), this.getPostitionY(), this.getLengthA(), this.getLengthB());
    }
}
