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

@XmlRootElement(name = "line")
@XmlAccessorType(XmlAccessType.FIELD)
public class Line extends Shape {

    @XmlAttribute(name = "x1")
    private int positionX1;
    @XmlAttribute(name = "y1")
    private int positionY1;
    @XmlAttribute(name = "x2")
    private int positionX2;
    @XmlAttribute(name = "y2")
    private int positionY2;

    public Line(int positionX, int positionY, int positionX2, int positionY2, String borderColor, float strokeWidth) {
        super(positionX, positionY, borderColor, null, strokeWidth);
        this.positionX1 = positionX;
        this.positionY1 = positionY;
        this.positionX2 = positionX2;
        this.positionY2 = positionY2;
    }

    public Line() {
        super(0, 0, "", "", 0);
        this.positionX2 = 0;
        this.positionY2 = 0;
    }

    public int getPositionX1() {
        return positionX1;
    }

    public void setPositionX1(int positionX1) {
        this.positionX1 = positionX1;
    }

    public int getPositionY1() {
        return positionY1;
    }

    public void setPositionY1(int positionY1) {
        this.positionY1 = positionY1;
    }

    @Override()
    @XmlTransient()
    public int getPositionX() {
        return this.positionX1;
    }

    @Override()
    @XmlTransient()
    public int getPositionY() {
        return this.positionY1;
    }

    public int getPositionX2() {
        return positionX2;
    }

    public int getPositionY2() {
        return positionY2;
    }

    public void setPositionX2(int positionX2) {
        this.positionX2 = positionX2;
    }

    public void setPositionY2(int positionY2) {
        this.positionY2 = positionY2;
    }

    @Override
    @Deprecated()
    /**
     * @deprecated Čára nepotřebuje fillColor.
     */
    @XmlTransient()
    public String getFillColor() {
        return null;
    }

    @Override
    @Deprecated()
    /**
     * @deprecated Čára nepotřebuje fillColor.
     */
    public void setFillColor(String fillColor) {
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(getStrokeWidth()));
        g2d.setColor(Color.decode(getborderColor()));
        g2d.drawLine(getPositionX(), getPositionY(), getPositionX2(), getPositionY2());
    }

    @Override
    public void calculatePositionFromCenter(int mouseX, int mouseY) {
        // FIXME jeden z bodu je vždy v počátku
        // Výpočet středového bodu
        int xCenter = mouseX;
        int yCenter = mouseY;
        // Výpočet nových souřadnic prvního bodu (positionX) tak, aby střed ležel na
        // (mouseX, mouseY)
        int newPositionX = 2 * xCenter - positionX2;
        int newPositionY = 2 * yCenter - positionY2;
        // Nastavení nových souřadnic prvního bodu (positionX)
        setPositionX(newPositionX);
        setPositionY(newPositionY);
    }

    @Override
    public void calculatePositionAndSizeFromStartEndPoints(Point start, Point end) {
        setPositionX(start.x);
        setPositionY(start.y);
        setPositionX2(end.x);
        setPositionY2(end.y);
    }
}
