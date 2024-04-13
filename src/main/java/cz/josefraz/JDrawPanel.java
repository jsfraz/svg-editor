package cz.josefraz;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import cz.josefraz.shapes.*;

public class JDrawPanel extends JPanel {

    private String backgroundColor;
    private ArrayList<Shape> shapes;

    public JDrawPanel(String backgroundColor) {
        super();
        this.backgroundColor = backgroundColor;
        try {
            Color.decode(backgroundColor);
        } catch (Exception e) {
            this.backgroundColor = "#FFFFFF";
        }
        setBackground(Color.decode(backgroundColor));
        this.shapes = Singleton.GetInstance().getShapes();
    }

    public void refershShapes() {
        this.shapes = Singleton.GetInstance().getShapes();
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
            shape.draw(g);
        }
    }
}
