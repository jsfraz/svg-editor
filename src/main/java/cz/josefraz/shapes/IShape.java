package cz.josefraz.shapes;

import java.awt.Graphics;

public interface IShape {

    public void draw(Graphics g);

    public void calculateMiddle(int mouseX, int mouseY);
}
