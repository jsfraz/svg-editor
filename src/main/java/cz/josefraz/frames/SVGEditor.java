package cz.josefraz.frames;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import cz.josefraz.components.JDrawPanel;
import cz.josefraz.components.JEditSplitPane;
import cz.josefraz.shapes.*;
import cz.josefraz.utils.FileUtils;
import cz.josefraz.utils.SVGUtils;
import cz.josefraz.utils.ShapeUtils;
import cz.josefraz.utils.Singleton;

public class SVGEditor extends JFrame {

    private JMenuItem saveMenuItem;
    private JMenuItem saveAsMenuItem;
    private JMenu codeMenu;
    private JMenu shapeMenu;
    private JMenu toolMenu;
    private JMenu backgroundMenu;

    private RSyntaxTextArea codeArea;
    private JDrawPanel drawPanel;
    private JEditSplitPane editSplitPane;
    private JSplitPane mainSplitPane;

    public SVGEditor() {
        super("SVG Editor");

        UIManager.put("TabbedPane.selected", Color.white);
        UIManager.put("TabbedPane.focus", Color.white);

        // Vytvoření menu
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Soubor");
        JMenuItem newFile = new JMenuItem("Nový");
        newFile.addActionListener(e -> {
            if (mainSplitPane == null) {
                // TODO dialog nového souboru
                addMainSplitPane();
                enableDisableMenuButtons(true);
            } else {
                int option = JOptionPane.showConfirmDialog(null, "Chcete pokračovat bez uložení změn?",
                        "Data budou ztracena", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    Singleton.GetInstance().setShapes(new ArrayList<>());
                    remove(mainSplitPane);
                    mainSplitPane = null;
                    enableDisableMenuButtons(false);
                    revalidate();
                    repaint();
                    // znovupřidání
                    addMainSplitPane();
                    enableDisableMenuButtons(true);
                }
            }
        });
        fileMenu.add(newFile);
        JMenuItem openMenuItem = new JMenuItem("Otevřít (SVG)");
        openMenuItem.addActionListener(e -> {
            if (mainSplitPane != null) {
                int option = JOptionPane.showConfirmDialog(null, "Chcete pokračovat bez uložení změn?",
                        "Data budou ztracena", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    Singleton.GetInstance().setShapes(new ArrayList<>());
                    remove(mainSplitPane);
                    mainSplitPane = null;
                    enableDisableMenuButtons(false);
                    revalidate();
                    repaint();
                    // TODO dialog otevření
                }
            } else {
                // TODO dialog otevření
            }
        });
        saveMenuItem = new JMenuItem("Uložit");
        saveMenuItem.addActionListener(e -> {
            // TODO uložení souboru
        });
        saveAsMenuItem = new JMenuItem("Uložit jako...");
        saveAsMenuItem.addActionListener(e -> {
            // TODO uložení souboru jako...
        });
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(saveAsMenuItem);
        shapeMenu = new JMenu("Tvary");
        JMenuItem circleItem = new JMenuItem("Kruh");
        circleItem.addActionListener(e -> {
            setEnabled(false);
            new NewShapeDialog(this, new Circle());
        });
        shapeMenu.add(circleItem);
        JMenuItem rectangleItem = new JMenuItem("TODO Obdélník");
        rectangleItem.addActionListener(e -> {
            setEnabled(false);
            new NewShapeDialog(this, new Rectangle());
        });
        shapeMenu.add(rectangleItem);
        JMenuItem squareItem = new JMenuItem("TODO Čtverec");
        squareItem.addActionListener(e -> {
            setEnabled(false);
            new NewShapeDialog(this, new Square());
        });
        shapeMenu.add(squareItem);
        JMenuItem ovalItem = new JMenuItem("Ovál");
        ovalItem.addActionListener(e -> {
            setEnabled(false);
            new NewShapeDialog(this, new Oval());
        });
        shapeMenu.add(ovalItem);
        JMenuItem lineItem = new JMenuItem("TODO Čára");
        lineItem.addActionListener(e -> {
            setEnabled(false);
            new NewShapeDialog(this, new Line());
        });
        shapeMenu.add(lineItem);
        codeMenu = new JMenu("Kód");
        JMenuItem formatCode = new JMenuItem("Formátovat");
        formatCode.addActionListener(e -> {
            // Formátování
            try {
                String beautified = SVGUtils.beautifySVG(SVGUtils.optimizeSVG(codeArea.getText()));
                codeArea.setText(beautified);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Formátování SVG", JOptionPane.ERROR_MESSAGE);
            }
        });
        codeMenu.add(formatCode);
        JMenuItem optimalizeCode = new JMenuItem("Optimalizovat");
        optimalizeCode.addActionListener(e -> {
            // Optimalizace
            try {
                SVGUtils.parseSVG(codeArea.getText());
                codeArea.setText(SVGUtils.optimizeSVG(codeArea.getText()));
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Optimalizace SVG", JOptionPane.ERROR_MESSAGE);
            }
        });
        codeMenu.add(optimalizeCode);
        JMenuItem validateCode = new JMenuItem("Validovat");
        validateCode.addActionListener(e -> {
            // Validace
            try {
                SVGUtils.parseSVG(codeArea.getText());
                JOptionPane.showMessageDialog(null, "SVG kód je validní.", "Validace SVG", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Validace SVG", JOptionPane.ERROR_MESSAGE);
            }
        });
        codeMenu.add(validateCode);
        toolMenu = new JMenu("Nástroje");
        JMenuItem randomShapes = new JMenuItem("Generovat náhodné tvary");
        randomShapes.addActionListener(e -> {
            setEnabled(false);
            new NumberInputDialog(1, 100, "Počet tvarů", "Generovat", this);
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
        backgroundMenu = new JMenu("Pozadí");
        JMenuItem transparentBackground = new JMenuItem("Průhledné");
        transparentBackground.addActionListener(e -> {
            drawPanel.setTransparentBackground();
        });
        backgroundMenu.add(transparentBackground);
        JMenuItem chooseBackgroundColor = new JMenuItem("Vybrat...");
        chooseBackgroundColor.addActionListener(e -> {
            // Zobrazit dialog pro výběr barvy
            Color selectedColor = JColorChooser.showDialog(this, "Barva pozadí", Color.WHITE, false);
            if (selectedColor != null) {
                drawPanel.setBackgroundColor(String.format("#%02x%02x%02x", selectedColor.getRed(),
                        selectedColor.getGreen(), selectedColor.getBlue()));
            }
        });
        backgroundMenu.add(chooseBackgroundColor);
        JMenuItem randomBackground = new JMenuItem("Náhodné");
        randomBackground.addActionListener(e -> {
            drawPanel.setBackgroundColor(ShapeUtils.generateRandomColor());
        });
        backgroundMenu.add(randomBackground);
        menuBar.add(fileMenu);
        menuBar.add(codeMenu);
        menuBar.add(shapeMenu);
        menuBar.add(toolMenu);
        menuBar.add(backgroundMenu);
        setJMenuBar(menuBar);

        // Zakázání menu tlačítek
        enableDisableMenuButtons(false);

        // Nastavení okna
        setVisible(true);
        setMinimumSize(new Dimension(1024, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }

    public JDrawPanel getDrawPanel() {
        return drawPanel;
    }

    public JEditSplitPane getEditSplitPane() {
        return editSplitPane;
    }

    private void addMainSplitPane() {
        // Kód SVG
        this.codeArea = new RSyntaxTextArea();
        codeArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_XML);
        // TODO odstranit ukázku kódu
        this.codeArea.setText(FileUtils.readTextFile("test.svg"));
        RTextScrollPane codeAreaScroll = new RTextScrollPane(codeArea);
        // Pravý JSplitPanel pro editaci
        this.editSplitPane = new JEditSplitPane();
        // Inicializace DrawPanelu, přidání tvarů
        this.drawPanel = new JDrawPanel(editSplitPane);
        // Vytvoření a konfigurace TabbedPane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Kód", codeAreaScroll);
        tabbedPane.addTab("Náhled", drawPanel);
        // Vytvoření SplitPane s rozdělením
        this.mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tabbedPane, this.editSplitPane);
        this.mainSplitPane.setResizeWeight(0.9); // Rozdělení 90% pro drawPanel, 10% pro rightSplitPane
        this.mainSplitPane.setDividerSize(3);
        // Přidání TabbedPane do JFrame
        add(this.mainSplitPane);
        revalidate();
        repaint();
    }

    private void enableDisableMenuButtons(boolean enable) {
        saveMenuItem.setEnabled(enable);
        saveAsMenuItem.setEnabled(enable);
        codeMenu.setEnabled(enable);
        shapeMenu.setEnabled(enable);
        toolMenu.setEnabled(enable);
        backgroundMenu.setEnabled(enable);
    }
}
