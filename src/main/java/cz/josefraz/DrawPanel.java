package cz.josefraz;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import cz.josefraz.shapes.*;

public class DrawPanel extends JPanel {

    private ArrayList<Shape> shapes;
    private Color backgroundColor;

    public DrawPanel(String backgroundColor, Shape... shapes) {
        super();
        this.shapes = new ArrayList<>(Arrays.asList(shapes));
        try {
            this.backgroundColor = Color.decode(backgroundColor);
            this.setBackground(this.backgroundColor);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Chyba", JOptionPane.ERROR_MESSAGE);
        }
    }

    public DrawPanel() {
        super();
        this.shapes = new ArrayList<>();
        this.backgroundColor = Color.white;
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
            shape.draw(g);
        }
    }
}
