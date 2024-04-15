package cz.josefraz.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import cz.josefraz.shapes.*;
import cz.josefraz.utils.Singleton;

public class JDrawPanel extends JPanel {

    public static final boolean defaultUseTransparentBackground = true;

    private String backgroundColor;
    private Image backgroundImage;
    private boolean useTransparentBackground;

    public JDrawPanel(JEditSplitPane editSplitPane, int sizeX, int sizeY) {
        super();
        this.backgroundColor = "#FFFFFF";
        this.backgroundImage = new ImageIcon(getClass().getResource("/transparency.png")).getImage();
        this.useTransparentBackground = defaultUseTransparentBackground;

        /*
        // Click event
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Shape randomShape = ShapeUtils.generateRandomShape();
                randomShape.calculateMiddle(e.getX(), e.getY());
                Singleton.GetInstance().addShape(randomShape);
                repaint();
                editSplitPane.refreshTables();
            }
        });
        */

        repaint();

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
    }
}
