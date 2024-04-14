package cz.josefraz.frames;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import cz.josefraz.components.JDrawPanel;
import cz.josefraz.components.JEditSplitPane;
import cz.josefraz.utils.ShapeUtils;
import cz.josefraz.utils.Singleton;

public class SVGEditor extends JFrame {

    private JDrawPanel drawPanel;
    private JEditSplitPane editSplitPane;

    public SVGEditor() {
        super("SVG Editor");

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
        JMenu shapeMenu = new JMenu("Tvary");
        JMenuItem circleItem = new JMenuItem("Kruh");
        circleItem.addActionListener(e -> {
            // TODO kruh
        });
        shapeMenu.add(circleItem);
        JMenuItem rectangleItem = new JMenuItem("Obdélník");
        rectangleItem.addActionListener(e -> {
            // TODO obdélník
        });
        shapeMenu.add(rectangleItem);
        JMenuItem squareItem = new JMenuItem("Čtverec");
        squareItem.addActionListener(e -> {
            // TODO čtverec
        });
        shapeMenu.add(squareItem);
        JMenuItem ovalItem = new JMenuItem("Ovál");
        ovalItem.addActionListener(e -> {
            // TODO ovál
        });
        shapeMenu.add(ovalItem);
        JMenuItem lineItem = new JMenuItem("Čára");
        lineItem.addActionListener(e -> {
            // TODO čára
        });
        shapeMenu.add(lineItem);
        JMenu toolMenu = new JMenu("Nástroje");
        JMenuItem randomShapes = new JMenuItem("Generovat náhodné tvary");
        randomShapes.addActionListener(e -> {
            // TODO nechat uživatele vybrat počet
            Singleton.GetInstance().addShapes(ShapeUtils.generateRandomShapes(100));
            // Refresh
            drawPanel.repaint();
            editSplitPane.refreshTables();
        });
        toolMenu.add(randomShapes);
        JMenuItem clear = new JMenuItem("Vyčistit plátno");
        clear.addActionListener(e -> {
            Singleton.GetInstance().setShapes(new ArrayList<>());
            // Refresh
            drawPanel.repaint();
            editSplitPane.refreshTables();
        });
        toolMenu.add(clear);
        menuBar.add(fileMenu);
        menuBar.add(shapeMenu);
        menuBar.add(toolMenu);
        setJMenuBar(menuBar);

        // Kód SVG
        JTextArea codeArea = new JTextArea();

        // Pravý JSplitPanel pro editaci
        this.editSplitPane = new JEditSplitPane();

        // Inicializace DrawPanelu, přidání tvarů
        this.drawPanel = new JDrawPanel("#FFFFFF", editSplitPane);

        // Vytvoření a konfigurace TabbedPane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Kód", codeArea);
        tabbedPane.addTab("Náhled", drawPanel);

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
