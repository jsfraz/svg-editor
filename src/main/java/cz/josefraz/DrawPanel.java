package cz.josefraz;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPanel;

public class DrawPanel extends JPanel {
    private ArrayList<Shape> shapes;

    public DrawPanel(Shape... shapes) {
        super();
        this.shapes = new ArrayList<>(Arrays.asList(shapes));
    }

    public DrawPanel() {
        this.shapes = new ArrayList<>();
    }

    public ArrayList<Shape> getShapes() {
        return shapes;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Následuje specifické vykreslování
        
        for (Shape shape : this.shapes) {
            // System.out.println(shape.getShapeName());
            switch (shape.getShapeName()) {
                case "Circle":
                    // Vykreslení kruhu
                    Circle c = (Circle)shape;
                    g.drawOval(c.getPositionX(), c.getPostitionY(), c.getDiameter(), c.getDiameter());
                    break;

                case "Rectangle":
                    // Vykreslení obdélníku
                    Rectangle r = (Rectangle)shape;
                    g.drawRect(r.getPositionX(), r.getPostitionY(), r.getLengthA(),
                    r.getLengthB());
                    break;

                case "Square":
                    // Vykreslení čtverce
                    Square s = (Square)shape;
                    g.drawRect(s.getPositionX(), s.getPostitionY(), s.getLengthA(),
                    s.getLengthA());
                    break;
            }
        }
    }
}
