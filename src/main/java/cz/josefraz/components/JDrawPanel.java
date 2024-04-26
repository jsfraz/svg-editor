package cz.josefraz.components;

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

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import cz.josefraz.shapes.Canvas;
import cz.josefraz.shapes.Shape;
import cz.josefraz.utils.Singleton;
import cz.josefraz.utils.XMLUtils;

public class JDrawPanel extends JPanel {

    private Image backgroundImage;

    private Point startPoint;
    private Point endPoint;
    private Shape drawnShape = null;
    private boolean drawing = false;

    private RSyntaxTextArea codeArea;

    public JDrawPanel(JEditSplitPane editSplitPane, RSyntaxTextArea codeArea) {
        super();
        this.backgroundImage = new ImageIcon(getClass().getResource("/transparency.png")).getImage();
        this.codeArea = codeArea;

        /*
         * // Přidání random tvaru při kliknutí
         * addMouseListener(new MouseAdapter() {
         * 
         * @Override
         * public void mouseClicked(MouseEvent e) {
         * Shape randomShape = ShapeUtils.generateRandomShape();
         * randomShape.calculatePositionFromCenter(e.getX(), e.getY());
         * Singleton.GetInstance().addShape(randomShape);
         * repaint();
         * editSplitPane.refreshTables();
         * }
         * });
         */

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
                    drawnShape = null;
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

        codeArea.setText(XMLUtils.getXml(Canvas.getImage()));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Přetypování
        Graphics2D g2d = (Graphics2D) g;
        // Zapnutí antialiasingu
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // "Průhledné" pozadí
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

        // Vykreslování tvarů
        for (Shape shape : Singleton.GetInstance().getShapes()) {
            shape.draw(g2d);
        }

        // Nakreslení tvaru myší
        if (startPoint != null && endPoint != null && drawing) {
            drawnShape.draw(g2d);
        }

        g2d.dispose();

        codeArea.setText(XMLUtils.getXml(Canvas.getImage()));
    }

    // Nastavení kresleného tvaru
    public void setDrawnShape(Shape shape) {
        this.drawnShape = shape;
        setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
    }
}
