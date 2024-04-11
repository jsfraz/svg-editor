package cz.josefraz;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPanel;

import cz.josefraz.shapes.*;

public class JDrawPanel extends JPanel {

    private ArrayList<Shape> shapes;
    private String backgroundColor;

    public JDrawPanel(String backgroundColor, Shape... shapes) {
        super();
        this.shapes = new ArrayList<>(Arrays.asList(shapes));
        this.backgroundColor = backgroundColor;
        try {
            Color.decode(backgroundColor);
        } catch (Exception e) {
            this.backgroundColor = "#FFFFFF";
        }
        setBackground(Color.decode(backgroundColor));
    }

    public JDrawPanel() {
        super();
        this.shapes = new ArrayList<>();
        this.backgroundColor = "#FFFFFF";
        setBackground(Color.decode(backgroundColor));
    }

    public ArrayList<Shape> getShapes() {
        return shapes;
    }

    public String getBackgroundColor() {
        return backgroundColor;
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
