package cz.josefraz;

import java.awt.Dimension;

import javax.swing.JFrame;

public class SVGEditor extends JFrame {

    private DrawPanel drawPanel;

    public SVGEditor() {
        super("SVG Editor");

        this.drawPanel = new DrawPanel(new Circle(50, 30, "#0000FF", 20), new Rectangle(20, 60, "#FF0000", 80, 40),
                new Square(80, 90, "#00FF00", 40));
        add(drawPanel);

        setVisible(true);
        setMinimumSize(new Dimension(400, 300));
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }
}
