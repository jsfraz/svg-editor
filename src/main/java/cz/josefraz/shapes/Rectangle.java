package cz.josefraz.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "rect")
@XmlAccessorType(XmlAccessType.FIELD)
public class Rectangle extends Shape {

    @XmlAttribute(name = "width")
    private int lengthX;
    @XmlAttribute(name = "height")
    private int lengthY;

    public Rectangle(int positionX, int positionY, String borderColor, String fillColor, int lengthA, int lengthB,
            float strokeWidth) {
        super(positionX, positionY, borderColor, fillColor, strokeWidth);
        this.lengthX = lengthA;
        this.lengthY = lengthB;
    }

    public Rectangle() {
        super(0, 0, "", "", 0);
        this.lengthX = 0;
        this.lengthY = 0;
    }

    public int getLengthX() {
        return lengthX;
    }

    public int getLengthY() {
        return lengthY;
    }

    public void setLengthX(int lengthA) {
        this.lengthX = lengthA;
    }

    public void setLengthY(int lengthB) {
        this.lengthY = lengthB;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.decode(getFillColor()));
        g2d.fillRect(getPositionX(), getPositionY(), getLengthX(), getLengthY());
        g2d.setStroke(new BasicStroke(getStrokeWidth()));
        g2d.setColor(Color.decode(getborderColor()));
        g2d.drawRect(getPositionX(), getPositionY(), getLengthX(), getLengthY());
    }

    @Override
    public void calculatePositionFromCenter(int mouseX, int mouseY) {
        // Výpočet středového bodu
        int xCenter = mouseX;
        int yCenter = mouseY;
        // Výpočet nových souřadnic obdélníku tak, aby střed ležel na (mouseX, mouseY)
        int newPositionX = xCenter - lengthX / 2;
        int newPositionY = yCenter - lengthY / 2;
        // Nastavení nových souřadnic obdélníku
        setPositionX(newPositionX);
        setPositionY(newPositionY);
    }

    @Override
    public void calculatePositionAndSizeFromStartEndPoints(Point start, Point end) {
        // Vypočítání šířky a výšky obdélníku
        setLengthX(Math.abs(end.x - start.x));
        setLengthY(Math.abs(end.y - start.y));
        // Vypočítání levého horního rohu obdélníku
        setPositionX(Math.min(start.x, end.x));
        setPositionY(Math.min(start.y, end.y));
    }
}
