package cz.josefraz.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "circle")
@XmlAccessorType(XmlAccessType.FIELD)
public class Circle extends Shape {

    @XmlAttribute(name="cx")
    private int positionX;
    @XmlAttribute(name="cy")
    private int positionY;
    @XmlAttribute(name = "r")
    private int radius;

    public Circle(int positionX, int positionY, String borderColor, String fillColor, int radius, float strokeWidth) {
        super(positionX, positionY, borderColor, fillColor, strokeWidth);
        this.positionX = positionX;
        this.positionY = positionY;
        this.radius = radius;
    }

    public Circle() {
        super(0, 0, "", "", 0);
        this.radius = 0;
    }

    @Override()
    @XmlTransient()
    public int getPositionX() {
        return this.positionX;
    }

    @Override()
    @XmlTransient()
    public int getPositionY() {
        return this.positionY;
    }

    public int getradius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.decode(getFillColor()));
        g2d.fillOval(getPositionX(), getPositionY(), getradius() * 2, getradius() * 2);
        g2d.setStroke(new BasicStroke(getStrokeWidth()));
        g2d.setColor(Color.decode(getborderColor()));
        g2d.drawOval(getPositionX(), getPositionY(), getradius() * 2, getradius() * 2);
    }

    @Override
    public void calculatePositionFromCenter(int mouseX, int mouseY) {
        setPositionX(mouseX - getradius());
        setPositionY(mouseY - getradius());
    }

    @Override
    public void calculatePositionAndSizeFromStartEndPoints(Point start, Point end) {
        // Vypočítání šířky a výšky kruhu
        int width = Math.abs(end.x - start.x);
        int height = Math.abs(end.y - start.y);
        // Vypočítání poloměru kruhu jako polovinu menší strany
        setRadius(Math.min(width, height) / 2);
        // Vypočítání levého horního rohu kruhu
        setPositionX(start.x < end.x ? start.x : start.x - getradius() * 2);
        setPositionY(start.y < end.y ? start.y : start.y - getradius() * 2);
    }
}
