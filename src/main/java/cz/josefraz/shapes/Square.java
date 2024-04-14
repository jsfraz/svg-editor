package cz.josefraz.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Square extends Shape {

    private int lengthA;

    public Square(int positionX, int postitionY, String borderColor, String fillColor, int lengthA, float strokeWidth) {
        super(positionX, postitionY, borderColor, fillColor, strokeWidth);
        this.lengthA = lengthA;
    }

    public int getLengthA() {
        return lengthA;
    }

    public void setLengthA(int lengthA) {
        this.lengthA = lengthA;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.decode(this.getFillColor()));
        g2d.fillRect(this.getPositionX(), this.getPostitionY(), this.getLengthA(), this.getLengthA());
        g2d.setStroke(new BasicStroke(this.getStrokeWidth()));
        g2d.setColor(Color.decode(this.getborderColor()));
        g2d.drawRect(this.getPositionX(), this.getPostitionY(), this.getLengthA(), this.getLengthA());
    }
}
