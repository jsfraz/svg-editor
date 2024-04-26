package cz.josefraz.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="rect")
@XmlAccessorType(XmlAccessType.FIELD)
public class Square extends Shape {

    @XmlAttribute(name="width")
    private int lengthX;

    public Square(int positionX, int positionY, String borderColor, String fillColor, int lengthA, float strokeWidth) {
        super(positionX, positionY, borderColor, fillColor, strokeWidth);
        this.lengthX = lengthA;
    }

    public Square() {
        super(0, 0, "", "", 0);
        this.lengthX = 0;
    }

    public int getLengthX() {
        return lengthX;
    }

    public void setLengthX(int lengthA) {
        this.lengthX = lengthA;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.decode(getFillColor()));
        g2d.fillRect(getPositionX(), getPositionY(), getLengthX(), getLengthX());
        g2d.setStroke(new BasicStroke(getStrokeWidth()));
        g2d.setColor(Color.decode(getborderColor()));
        g2d.drawRect(getPositionX(), getPositionY(), getLengthX(), getLengthX());
    }

    @Override
    public void calculatePositionFromCenter(int mouseX, int mouseY) {
        // Výpočet středového bodu
        int xCenter = mouseX;
        int yCenter = mouseY;
        // Výpočet nových souřadnic čtverce tak, aby střed ležel na (mouseX, mouseY)
        int newPositionX = xCenter - lengthX / 2;
        int newPositionY = yCenter - lengthX / 2;
        // Nastavení nových souřadnic čtverce
        setPositionX(newPositionX);
        setPositionY(newPositionY);
    }

    @Override
    public void calculatePositionAndSizeFromStartEndPoints(Point start, Point end) {
        // Vypočítání šířky a výšky čtverce
        int width = Math.abs(end.x - start.x);
        int height = Math.abs(end.y - start.y);
        // Vypočítání délky strany čtverce jako poloviny menší strany
        setLengthX(Math.min(width, height));
        // Vypočítání levého horního rohu čtverce
        setPositionX(start.x < end.x ? start.x : start.x - getLengthX() * 2);
        setPositionY(start.y < end.y ? start.y : start.y - getLengthX() * 2);
    }

    // Povinný atribut height
    @XmlAttribute(name="height")
    public int getHeight() {
        return getLengthX();
    }
}
