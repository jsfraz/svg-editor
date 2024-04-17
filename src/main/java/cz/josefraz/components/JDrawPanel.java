package cz.josefraz.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import cz.josefraz.shapes.Shape;
import cz.josefraz.utils.Singleton;

public class JDrawPanel extends JPanel {

    public static final boolean defaultUseTransparentBackground = true;

    private String backgroundColor;
    private Image backgroundImage;
    private boolean useTransparentBackground;

    private Point startPoint;
    private Point endPoint;
    private Shape drawnShape;
    // TODO kreslící kurzor

    public JDrawPanel(JEditSplitPane editSplitPane) {
        super();
        this.backgroundColor = "#FFFFFF";
        this.backgroundImage = new ImageIcon(getClass().getResource("/transparency.png")).getImage();
        this.useTransparentBackground = defaultUseTransparentBackground;

        /*
         * // Click event
         * addMouseListener(new MouseAdapter() {
         * 
         * @Override
         * public void mouseClicked(MouseEvent e) {
         * Shape randomShape = ShapeUtils.generateRandomShape();
         * randomShape.calculateMiddle(e.getX(), e.getY());
         * Singleton.GetInstance().addShape(randomShape);
         * repaint();
         * editSplitPane.refreshTables();
         * }
         * });
         */

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startPoint = e.getPoint();
                endPoint = startPoint;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                endPoint = e.getPoint();
                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                endPoint = e.getPoint();
                repaint();
            }
        });

        // repaint();

        // TODO scroll panelu
    }

    // Nastavení "průhledné" na pozadí
    public void setTransparentBackground() {
        useTransparentBackground = true;
        repaint();
    }

    public void setBackgroundColor(String hexColor) {
        useTransparentBackground = false;
        backgroundColor = hexColor;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Průhledné pozadí nebo barva
        if (useTransparentBackground) {
            if (backgroundImage != null) {
                // Opakující se obrázek na pozadí
                int width = getWidth();
                int height = getHeight();
                for (int x = 0; x < width; x += backgroundImage.getWidth(this)) {
                    for (int y = 0; y < height; y += backgroundImage.getHeight(this)) {
                        g.drawImage(backgroundImage, x, y, this);
                    }
                }
            }
        } else {
            setBackground(Color.decode(backgroundColor));
        }

        // Vykreslování tvarů
        for (Shape shape : Singleton.GetInstance().getShapes()) {
            shape.draw(g);
        }

        if (startPoint != null && endPoint != null) {
            int width = Math.abs(endPoint.x - startPoint.x);
            int height = Math.abs(endPoint.y - startPoint.y);
            int x = Math.min(startPoint.x, endPoint.x);
            int y = Math.min(startPoint.y, endPoint.y);
            g.drawRect(x, y, width, height);
        }
    }

    public void setDrawnShape(Shape shape) {

    }
}
