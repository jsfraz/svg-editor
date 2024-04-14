package cz.josefraz.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Square extends Shape {

    private int lengthA;

    public Square(int positionX, int postitionY, String borderColor, String fillColor, int lengthA, float strokeWidth) {
        super(positionX, postitionY, borderColor, fillColor, strokeWidth);
        this.lengthA = lengthA;
    }

    public int getLengthA() {
        return lengthA;
    }

    public void setLengthA(int lengthA) {
        this.lengthA = lengthA;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.decode(getFillColor()));
        g2d.fillRect(getPositionX(), getPositionY(), getLengthA(), getLengthA());
        g2d.setStroke(new BasicStroke(getStrokeWidth()));
        g2d.setColor(Color.decode(getborderColor()));
        g2d.drawRect(getPositionX(), getPositionY(), getLengthA(), getLengthA());
    }

    @Override
public void calculateMiddle(int mouseX, int mouseY) {
    // Výpočet středového bodu
    int xCenter = mouseX;
    int yCenter = mouseY;
    // Výpočet nových souřadnic čtverce tak, aby střed ležel na (mouseX, mouseY)
    int newPositionX = xCenter - lengthA / 2;
    int newPositionY = yCenter - lengthA / 2;
    // Nastavení nových souřadnic čtverce
    setPositionX(newPositionX);
    setPositionY(newPositionY);
}

}
