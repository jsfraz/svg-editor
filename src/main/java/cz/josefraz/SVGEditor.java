package cz.josefraz;

import java.awt.Dimension;

import javax.swing.JFrame;

import cz.josefraz.shapes.*;

public class SVGEditor extends JFrame {

    private DrawPanel drawPanel;

    public SVGEditor() {
        super("SVG Editor");

        // Inicializace DrawPanelu, přidání tvarů
        this.drawPanel = new DrawPanel(new Circle(50, 30, "#0000FF", 20), new Rectangle(20, 60, "#FF0000", 80, 40),
                new Square(80, 90, "#00FF00", 40), new Oval(150, 250, "#123456", 60, 90),
                new Line(150, 100, "#654321", 400, 300));
        add(drawPanel);

        // Nastavení okna
        setVisible(true);
        setMinimumSize(new Dimension(400, 300));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }
}
