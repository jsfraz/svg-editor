package cz.josefraz.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Line extends Shape {

    private int postitionX2, positionY2;

    public Line(int positionX, int postitionY, String outlineColor, int postitionX2, int positionY2, float strokeWidth) {
        super(positionX, postitionY, outlineColor, "#ffffff", strokeWidth);
        this.postitionX2 = postitionX2;
        this.positionY2 = positionY2;
    }

    public int getPostitionX2() {
        return postitionX2;
    }

    public int getPositionY2() {
        return positionY2;
    }
    
    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(this.getStrokeWidth()));
        g2d.setColor(Color.decode(this.getOutlineColor()));
        g2d.drawLine(this.getPositionX(), this.getPostitionY(), this.getPostitionX2(), this.getPositionY2());
    }
}
