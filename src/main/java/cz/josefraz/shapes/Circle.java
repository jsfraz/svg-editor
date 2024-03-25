package cz.josefraz.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Circle extends Shape {

    private int diameter;

    public Circle(int positionX, int postitionY, String outlineColor, String fillColor, int diameter, float strokeWidth) {
        super(positionX, postitionY, outlineColor, fillColor, strokeWidth);
        this.diameter = diameter;
    }

    public int getDiameter() {
        return diameter;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.decode(this.getFillColor()));
        g2d.fillOval(this.getPositionX(), this.getPostitionY(), this.getDiameter(), this.getDiameter());
        g2d.setStroke(new BasicStroke(this.getStrokeWidth()));
        g2d.setColor(Color.decode(this.getOutlineColor()));
        g2d.drawOval(this.getPositionX(), this.getPostitionY(), this.getDiameter(), this.getDiameter());
    }
}
