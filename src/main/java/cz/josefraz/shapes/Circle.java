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
        g2d.setColor(Color.decode(getFillColor()));
        g2d.fillOval(getPositionX(), getPositionY(), getradius(), getradius());
        g2d.setStroke(new BasicStroke(getStrokeWidth()));
        g2d.setColor(Color.decode(getborderColor()));
        g2d.drawOval(getPositionX(), getPositionY(), getradius(), getradius());
    }

    @Override
    public void calculateMiddle(int mouseX, int mouseY) {
        setPositionX((int)(mouseX - getradius() * 0.5));
        setPositionY((int)(mouseY - getradius() * 0.5));
    }
}
