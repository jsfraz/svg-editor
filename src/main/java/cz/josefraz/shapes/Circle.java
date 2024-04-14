package cz.josefraz.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Circle extends Shape {

    private int radius;

    public Circle(int positionX, int postitionY, String borderColor, String fillColor, int radius, float strokeWidth) {
        super(positionX, postitionY, borderColor, fillColor, strokeWidth);
        this.radius = radius;
    }

    public int getradius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.decode(this.getFillColor()));
        g2d.fillOval(this.getPositionX(), this.getPostitionY(), this.getradius(), this.getradius());
        g2d.setStroke(new BasicStroke(this.getStrokeWidth()));
        g2d.setColor(Color.decode(this.getborderColor()));
        g2d.drawOval(this.getPositionX(), this.getPostitionY(), this.getradius(), this.getradius());
    }
}
