package cz.josefraz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import cz.josefraz.shapes.*;

public class SVGEditor extends JFrame {

    private JDrawPanel drawPanel;

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
        this.drawPanel = new JDrawPanel("#FFFFFF", new Circle(50, 30, "#0000FF", "#ff8503", 20, 1),
                new Rectangle(20, 60, "#240771", "#fedf00", 80, 40, 2),
                new Square(80, 90, "#8b4513", "#6a329f", 40, 3), new Oval(150, 250, "#274e13", "#cc0000", 60, 90, 2.5f),
                new Line(150, 100, "#a64d79", 400, 300, 15f));
    
        // Vytvoření panelu na pravou stranu
        JSplitPane rightSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        rightSplitPane.setResizeWeight(0.5); // Rozdělení 50% pro každou část
        rightSplitPane.setDividerSize(5);
    
        // Vytvoření a konfigurace TabbedPane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Kód", codeArea);
        tabbedPane.addTab("Náhled", drawPanel);
    
        // Vytvoření SplitPane s rozdělením 3:1
        JSplitPane mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tabbedPane, rightSplitPane);
        mainSplitPane.setResizeWeight(0.75); // Rozdělení 75% pro drawPanel, 25% pro rightSplitPane
        mainSplitPane.setDividerSize(5);
    
        // Přidání SplitPane do rightSplitPane
        JPanel shapesPanel = new JPanel(new BorderLayout());
        JLabel shapesLabel = new JLabel("Tvary");
        shapesLabel.setFont(shapesLabel.getFont().deriveFont(13.0f)); // Zvětšení velikosti písma
        shapesPanel.add(shapesLabel, BorderLayout.NORTH);
        shapesPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Odsazení od kraje
        rightSplitPane.setTopComponent(shapesPanel); // První komponenta (nahoru)
    
        JPanel attributesPanel = new JPanel(new BorderLayout());
        JLabel attributesLabel = new JLabel("Atributy");
        attributesLabel.setFont(attributesLabel.getFont().deriveFont(13.0f)); // Zvětšení velikosti písma
        attributesPanel.add(attributesLabel, BorderLayout.NORTH);
        attributesPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Odsazení od kraje
        rightSplitPane.setBottomComponent(attributesPanel); // Druhá komponenta (dolů)
    
        // Přidání TabbedPane do JFrame
        add(mainSplitPane);
    
        // Nastavení okna
        setVisible(true);
        setMinimumSize(new Dimension(1024, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }
}
