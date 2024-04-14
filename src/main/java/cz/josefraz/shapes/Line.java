package cz.josefraz.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Line extends Shape {

    private int postitionX2, positionY2;

    public Line(int positionX, int postitionY, int postitionX2, int positionY2, String borderColor, float strokeWidth) {
        super(positionX, postitionY, borderColor, null, strokeWidth);
        this.postitionX2 = postitionX2;
        this.positionY2 = positionY2;
    }

    public int getPostitionX2() {
        return postitionX2;
    }

    public int getPositionY2() {
        return positionY2;
    }

    public void setPostitionX2(int postitionX2) {
        this.postitionX2 = postitionX2;
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
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(this.getStrokeWidth()));
        g2d.setColor(Color.decode(this.getborderColor()));
        g2d.drawLine(this.getPositionX(), this.getPostitionY(), this.getPostitionX2(), this.getPositionY2());
    }
}
