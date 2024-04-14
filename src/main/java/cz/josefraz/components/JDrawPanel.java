package cz.josefraz.components;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import cz.josefraz.shapes.*;
import cz.josefraz.utils.Singleton;

public class JDrawPanel extends JPanel {

    private String backgroundColor;

    public JDrawPanel(String backgroundColor) {
        super();
        this.backgroundColor = backgroundColor;
        try {
            Color.decode(backgroundColor);
        } catch (Exception e) {
            this.backgroundColor = "#FFFFFF";
        }
        setBackground(Color.decode(backgroundColor));
        refreshShapes();

        // TODO scroll panelu
    }

    public void refreshShapes() {
        repaint();
    }

    public JDrawPanel() {
        super();
        this.backgroundColor = "#FFFFFF";
        setBackground(Color.decode(backgroundColor));
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Následuje specifické vykreslování

        for (Shape shape : Singleton.GetInstance().getShapes()) {
            shape.draw(g);
        }
    }
}
