package cz.josefraz.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import cz.josefraz.shapes.Shape;
import cz.josefraz.utils.ShapeUtils;
import cz.josefraz.utils.Singleton;

public class JDrawPanel extends JPanel {

    public static final boolean defaultUseTransparentBackground = true;

    private String backgroundColor;
    private Image backgroundImage;
    private boolean useTransparentBackground;

    private Point startPoint;
    private Point endPoint;
    private Shape drawnShape = null;
    private boolean drawing = false;

    public JDrawPanel(JEditSplitPane editSplitPane) {
        super();
        this.backgroundColor = "#FFFFFF";
        this.backgroundImage = new ImageIcon(getClass().getResource("/transparency.png")).getImage();
        this.useTransparentBackground = defaultUseTransparentBackground;

        // Přidání random tvaru při kliknutí
        addMouseListener(new MouseAdapter() {
        
        @Override
        public void mouseClicked(MouseEvent e) {
        Shape randomShape = ShapeUtils.generateRandomShape();
        randomShape.calculatePositionFromCenter(e.getX(), e.getY());
        Singleton.GetInstance().addShape(randomShape);
        repaint();
        editSplitPane.refreshTables();
        }
        });

        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                drawing = false;
                drawnShape = null;
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (!drawing && drawnShape != null) {
                    drawing = true;
                    startPoint = e.getPoint();
                    endPoint = startPoint;
                    repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (drawing) {
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    drawing = false;
                    drawnShape = null;
                    endPoint = e.getPoint();
                    // Kontroal souřadnic
                    if (endPoint.x == startPoint.x && endPoint.y == startPoint.y) {
                        JOptionPane.showMessageDialog(null, "Konečná souřadnice se rovná počáteční.",
                                "Nelze nakreslit tvar", JOptionPane.ERROR_MESSAGE);
                    } else {
                        Singleton.GetInstance().addShape(drawnShape);
                        editSplitPane.refreshTables();
                        repaint();
                    }
                }
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (drawing) {
                    endPoint = e.getPoint();
                    drawnShape.calculatePositionAndSizeFromStartEndPoints(startPoint, endPoint);
                    repaint();
                }
            }
        });
    }

    // Nastavení "průhledné" na pozadí
    public void setTransparentBackground() {
        useTransparentBackground = true;
        repaint();
    }

    // Nastavení barvy pozadí z hex stringu
    public void setBackgroundColor(String hexColor) {
        useTransparentBackground = false;
        backgroundColor = hexColor;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Přetypování
        Graphics2D g2d = (Graphics2D) g;
        // Zapnutí antialiasingu
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

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
            shape.draw(g2d);
        }

        // Nakreslení tvaru myší
        if (startPoint != null && endPoint != null && drawing) {
            drawnShape.draw(g2d);
        }

        g2d.dispose();
    }

    // Nastavení kresleného tvaru
    public void setDrawnShape(Shape shape) {
        this.drawnShape = shape;
        setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
    }
}
