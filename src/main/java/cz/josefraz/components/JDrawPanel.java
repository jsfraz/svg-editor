package cz.josefraz.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import cz.josefraz.shapes.*;
import cz.josefraz.utils.ShapeUtils;
import cz.josefraz.utils.Singleton;

public class JDrawPanel extends JPanel {

    private String backgroundColor;

    public JDrawPanel(String backgroundColor, JEditSplitPane editSplitPane) {
        super();
        this.backgroundColor = backgroundColor;
        try {
            Color.decode(backgroundColor);
        } catch (Exception e) {
            this.backgroundColor = "#FFFFFF";
        }
        setBackground(Color.decode(backgroundColor));

        // Click event
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Shape randomShape = ShapeUtils.generateRandomShape();
                randomShape.setPositionX(e.getX());
                randomShape.setPostitionY(e.getY());
                Singleton.GetInstance().addShape(randomShape);
                repaint();
                editSplitPane.refreshTables();
            }
        });

        repaint();

        // TODO scroll panelu
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
