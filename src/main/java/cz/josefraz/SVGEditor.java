package cz.josefraz;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import cz.josefraz.shapes.*;

public class SVGEditor extends JFrame {

    private Shape[] shapes;
    private JDrawPanel drawPanel;
    private JEditSplitPane editSplitPane;

    public SVGEditor() {
        super("SVG Editor");

        this.shapes = new Shape[] {
                new Circle(50, 30, "#0000FF", "#ff8503", 20, 1),
                new Rectangle(20, 60, "#240771", "#fedf00", 80, 40, 2),
                new Square(80, 90, "#8b4513", "#6a329f", 40, 3), new Oval(150, 250, "#274e13", "#cc0000", 60, 90, 2.5f),
                new Line(150, 100, "#a64d79", 400, 300, 15f),
        };

        UIManager.put("TabbedPane.selected", Color.white);
        UIManager.put("TabbedPane.focus", Color.white);

        // Vytvoření menu
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Soubor");
        JMenuItem openMenuItem = new JMenuItem("Otevřít (SVG)");
        openMenuItem.addActionListener(e -> {
            // TODO otevření souboru
        });
        JMenuItem saveMenuItem = new JMenuItem("Uložit");
        saveMenuItem.addActionListener(e -> {
            // TODO uložení souboru
        });
        JMenuItem saveAsMenuItem = new JMenuItem("Uložit jako...");
        saveAsMenuItem.addActionListener(e -> {
            // TODO uložení souboru jako...
        });
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(saveAsMenuItem);
        JMenu toolsMenu = new JMenu("Nástroje");
        JMenuItem circleItem = new JMenuItem("Kruh");
        circleItem.addActionListener(e -> {
            // TODO kruh
        });
        toolsMenu.add(circleItem);
        JMenuItem rectangleItem = new JMenuItem("Obdélník");
        rectangleItem.addActionListener(e -> {
            // TODO obdélník
        });
        toolsMenu.add(rectangleItem);
        JMenuItem squareItem = new JMenuItem("Čtverec");
        squareItem.addActionListener(e -> {
            // TODO čtverec
        });
        toolsMenu.add(squareItem);
        JMenuItem ovalItem = new JMenuItem("Ovál");
        ovalItem.addActionListener(e -> {
            // TODO ovál
        });
        toolsMenu.add(ovalItem);
        JMenuItem lineItem = new JMenuItem("Čára");
        lineItem.addActionListener(e -> {
            // TODO čára
        });
        toolsMenu.add(lineItem);
        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
        setJMenuBar(menuBar);

        // Kód SVG
        JTextArea codeArea = new JTextArea();

        // Inicializace DrawPanelu, přidání tvarů
        this.drawPanel = new JDrawPanel("#FFFFFF", this.shapes);

        // Vytvoření a konfigurace TabbedPane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Kód", codeArea);
        tabbedPane.addTab("Náhled", drawPanel);

        // Pravý JSplitPanel pro editaci
        this.editSplitPane = new JEditSplitPane(this.shapes);

        // Vytvoření SplitPane s rozdělením
        JSplitPane mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tabbedPane, this.editSplitPane);
        mainSplitPane.setResizeWeight(0.9); // Rozdělení 90% pro drawPanel, 10% pro rightSplitPane
        mainSplitPane.setDividerSize(3);

        // Přidání TabbedPane do JFrame
        add(mainSplitPane);

        // Nastavení okna
        setVisible(true);
        setMinimumSize(new Dimension(1024, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }
}
