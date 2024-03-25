package cz.josefraz.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Square extends Shape {

    private int lengthA;

    public Square(int positionX, int postitionY, String outlineColor, String fillColor, int lengthA, float strokeWidth) {
        super(positionX, postitionY, outlineColor, fillColor, strokeWidth);
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
        g2d.setColor(Color.decode(this.getOutlineColor()));
        g2d.drawRect(this.getPositionX(), this.getPostitionY(), this.getLengthA(), this.getLengthA());
    }
}
