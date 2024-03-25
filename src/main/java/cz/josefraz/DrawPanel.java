package cz.josefraz;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPanel;

import cz.josefraz.shapes.*;

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
            // https://docs.oracle.com/en/java/javase/17/docs/api/java.desktop/java/awt/Graphics.html

            // Vykreslování tvarů
            switch (shape.getShapeName()) {
                case "Circle":
                    // Kruh
                    Circle c = (Circle) shape;
                    g.drawOval(c.getPositionX(), c.getPostitionY(), c.getDiameter(), c.getDiameter());
                    break;

                case "Rectangle":
                    // Obdélník
                    Rectangle r = (Rectangle) shape;
                    g.drawRect(r.getPositionX(), r.getPostitionY(), r.getLengthA(),
                            r.getLengthB());
                    break;

                case "Square":
                    // Čtverec
                    Square s = (Square) shape;
                    g.drawRect(s.getPositionX(), s.getPostitionY(), s.getLengthA(),
                            s.getLengthA());
                    break;

                case "Oval":
                    // Ovál
                    Oval o = (Oval) shape;
                    g.drawOval(o.getPositionX(), o.getPostitionY(), o.getLengthA(),
                            o.getLengthB());
                    break;

                case "Line":
                    // Přímka
                    Line l = (Line) shape;
                    g.drawLine(l.getPositionX(), l.getPostitionY(), l.getPostitionX2(), l.getPositionY2());
                    break;
            }
        }
    }
}
