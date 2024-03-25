package cz.josefraz.frames;

import java.awt.Dimension;

import javax.swing.JFrame;

import cz.josefraz.shapes.*;

public class SVGEditor extends JFrame {

    private DrawPanel drawPanel;

    public SVGEditor() {
        super("SVG Editor");

        // Inicializace DrawPanelu, přidání tvarů
        this.drawPanel = new DrawPanel("#FFFFFF", new Circle(50, 30, "#0000FF", "#ff8503", 20, 1),
                new Rectangle(20, 60, "#240771", "#fedf00", 80, 40, 2),
                new Square(80, 90, "#8b4513", "#6a329f", 40, 3), new Oval(150, 250, "#274e13", "#cc0000", 60, 90, 2.5f),
                new Line(150, 100, "#a64d79", 400, 300, 15f));
        add(drawPanel);

        // Nastavení okna
        setVisible(true);
        setMinimumSize(new Dimension(400, 300));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }
}
