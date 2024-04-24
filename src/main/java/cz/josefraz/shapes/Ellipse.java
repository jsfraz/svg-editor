package cz.josefraz.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAccessType;

@XmlRootElement(name="ellipse")
@XmlAccessorType(XmlAccessType.FIELD)
public class Ellipse extends Shape {

    @XmlAttribute(name="cx")
    private int positionX;
    @XmlAttribute(name="cy")
    private int positionY;
    @XmlAttribute(name="rx")
    private int lengthX;
    @XmlAttribute(name="ry")
    private int lengthY;

    public Ellipse(int positionX, int positionY, String borderColor, String fillColor, int lengthA, int lengthB,
            float strokeWidth) {
        super(positionX, positionY, borderColor, fillColor, strokeWidth);
        this.positionX = positionX;
        this.positionY = positionY;
        this.lengthX = lengthA;
        this.lengthY = lengthB;
    }

    public Ellipse() {
        super(0, 0, "", "", 0);
        this.lengthX = 0;
        this.lengthY = 0;
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
        g2d.fillOval(getPositionX(), getPositionY(), getLengthX(), getLengthY());
        g2d.setStroke(new BasicStroke(getStrokeWidth()));
        g2d.setColor(Color.decode(getborderColor()));
        g2d.drawOval(getPositionX(), getPositionY(), getLengthX(), getLengthY());
    }

    @Override
    public void calculatePositionFromCenter(int mouseX, int mouseY) {
        // Výpočet středového bodu
        int xCenter = mouseX;
        int yCenter = mouseY;
        // Výpočet nových souřadnic elipsy tak, aby střed ležel na (mouseX, mouseY)
        int newPositionX = xCenter - lengthX / 2;
        int newPositionY = yCenter - lengthY / 2;
        // Nastavení nových souřadnic elipsy
        setPositionX(newPositionX);
        setPositionY(newPositionY);
    }

    @Override
    public void calculatePositionAndSizeFromStartEndPoints(Point start, Point end) {
        // Vypočítání šířky a výšky elipsy
        setLengthX(Math.abs(end.x - start.x));
        setLengthY(Math.abs(end.y - start.y));
        // Vypočítání levého horního rohu elipsy
        setPositionX(Math.min(start.x, end.x));
        setPositionY(Math.min(start.y, end.y));
    }
}
