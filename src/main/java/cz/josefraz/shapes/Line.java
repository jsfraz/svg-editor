package cz.josefraz.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Line extends Shape {

    private int positionX2, positionY2;

    public Line(int positionX, int postitionY, int postitionX2, int positionY2, String borderColor, float strokeWidth) {
        super(positionX, postitionY, borderColor, null, strokeWidth);
        this.positionX2 = postitionX2;
        this.positionY2 = positionY2;
    }
    
    public Line() {
        super(0, 0, "", "", 0);
        this.positionX2 = 0;
        this.positionY2 = 0;
    }

    public int getPositionX2() {
        return positionX2;
    }

    public int getPositionY2() {
        return positionY2;
    }

    public void setPositionX2(int postitionX2) {
        this.positionX2 = postitionX2;
    }

    public void setPositionY2(int positionY2) {
        this.positionY2 = positionY2;
    }

    @Override
    @Deprecated()
    /**
     * @deprecated Čára nepotřebuje fillColor.
     */
    public String getFillColor() {
        return null;
    }

    @Override
    @Deprecated()
    /**
     * @deprecated Čára nepotřebuje fillColor.
     */
    public void setFillColor(String fillColor) {}

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
        // Výpočet nových souřadnic prvního bodu (positionX) tak, aby střed ležel na (mouseX, mouseY)
        int newPositionX = 2 * xCenter - positionX2;
        int newPositionY = 2 * yCenter - positionY2;
        // Nastavení nových souřadnic prvního bodu (positionX)
        setPositionX(newPositionX);
        setPositionY(newPositionY);
    }

    @Override
    public void calculatePositionAndSizeFromStartEndPoints(Point start, Point end) {
        // TODO Auto-generated method stub
        
    }
}
