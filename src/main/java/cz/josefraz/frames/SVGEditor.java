package cz.josefraz.frames;

import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.JAXBException;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import cz.josefraz.components.JDrawPanel;
import cz.josefraz.components.JEditSplitPane;
import cz.josefraz.shapes.Canvas;
import cz.josefraz.shapes.Circle;
import cz.josefraz.shapes.Ellipse;
import cz.josefraz.shapes.Line;
import cz.josefraz.shapes.Rectangle;
import cz.josefraz.shapes.Square;
import cz.josefraz.utils.SVGUtils;
import cz.josefraz.utils.Singleton;
import cz.josefraz.utils.XMLUtils;

public class SVGEditor extends JFrame {

    private JMenu saveAsMenuItem;
    private JMenu codeMenu;
    private JMenu shapeMenu;
    private JMenu toolMenu;

    private JEditSplitPane editSplitPane;
    private JSplitPane mainSplitPane;

    public SVGEditor() {
        super("SVG Editor");

        // Barvy JTabbedPanelu
        UIManager.put("TabbedPane.selected", Color.white);
        UIManager.put("TabbedPane.focus", Color.white);

        // Vytvoření menu
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Soubor");
        JMenuItem newFile = new JMenuItem("Nový");
        newFile.addActionListener(e -> {
            if (mainSplitPane == null) {
                addMainSplitPane();
                enableDisableMenuButtons(true);
            } else {
                int option = JOptionPane.showConfirmDialog(null, "Chcete pokračovat bez uložení změn?",
                        "Data budou ztracena", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    Singleton.getInstance().setShapes(new ArrayList<>());
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
        JMenuItem openMenuItem = new JMenuItem("Otevřít SVG");
        openMenuItem.addActionListener(e -> {
            if (mainSplitPane != null) {
                int option = JOptionPane.showConfirmDialog(null,
                        "Chcete pokračovat bez uložení změn?",
                        "Data budou ztracena", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    Singleton.getInstance().setShapes(new ArrayList<>());
                    remove(mainSplitPane);
                    mainSplitPane = null;
                    enableDisableMenuButtons(false);
                    revalidate();
                    repaint();
                    
                    openSVG();
                }
            } else {
                // TODO dialog otevření
            }
        });
        fileMenu.add(openMenuItem);
        saveAsMenuItem = new JMenu("Uložit jako...");
        JMenuItem saveAsSVG = new JMenuItem("SVG");
        saveAsSVG.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("SVG soubory (*.svg)", "svg"));
            int returnValue = fileChooser.showSaveDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String filePath = selectedFile.getAbsolutePath();

                // Pokud neexistuje přípona .svg, přidej ji
                if (!filePath.toLowerCase().endsWith(".svg")) {
                    selectedFile = new File(filePath + ".svg");
                }

                // Uložení obsahu souboru
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile));
                    writer.write(XMLUtils.getXml(Canvas.getCanvas()));
                    writer.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Chyba při ukládání souboru.", "Chyba",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        saveAsMenuItem.add(saveAsSVG);
        JMenuItem saveAsJSON = new JMenuItem("JSON");
        saveAsSVG.addActionListener(e -> {
            // TODO JSON
        });
        saveAsMenuItem.add(saveAsJSON);
        fileMenu.add(saveAsMenuItem);
        shapeMenu = new JMenu("Tvary");
        JMenuItem circleItem = new JMenuItem("Kruh");
        circleItem.addActionListener(e -> {
            setEnabled(false);
            new NewShapeDialog(this, new Circle());
        });
        shapeMenu.add(circleItem);
        JMenuItem rectangleItem = new JMenuItem("Obdélník");
        rectangleItem.addActionListener(e -> {
            setEnabled(false);
            new NewShapeDialog(this, new Rectangle());
        });
        shapeMenu.add(rectangleItem);
        JMenuItem squareItem = new JMenuItem("Čtverec");
        squareItem.addActionListener(e -> {
            setEnabled(false);
            new NewShapeDialog(this, new Square());
        });
        shapeMenu.add(squareItem);
        JMenuItem ovalItem = new JMenuItem("Elipsa");
        ovalItem.addActionListener(e -> {
            setEnabled(false);
            new NewShapeDialog(this, new Ellipse());
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
                String beautified = SVGUtils
                        .beautifySVG(SVGUtils.optimizeSVG(Singleton.getInstance().getCodeArea().getText()));
                Singleton.getInstance().getCodeArea().setText(beautified);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Formátování SVG",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        codeMenu.add(formatCode);
        JMenuItem optimalizeCode = new JMenuItem("Optimalizovat");
        optimalizeCode.addActionListener(e -> {
            // Optimalizace
            try {
                SVGUtils.parseSVG(Singleton.getInstance().getCodeArea().getText());
                Singleton.getInstance().getCodeArea()
                        .setText(SVGUtils.optimizeSVG(Singleton.getInstance().getCodeArea().getText()));
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Optimalizace SVG",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        codeMenu.add(optimalizeCode);
        JMenuItem validateCode = new JMenuItem("Validovat");
        validateCode.addActionListener(e -> {
            // Validace
            try {
                SVGUtils.parseSVG(Singleton.getInstance().getCodeArea().getText());
                JOptionPane.showMessageDialog(null, "SVG kód je validní.", "Validace SVG",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Validace SVG",
                        JOptionPane.ERROR_MESSAGE);
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
            Singleton.getInstance().setShapes(new ArrayList<>());
            // Refresh
            Singleton.getInstance().getDrawPanel().repaint();
            editSplitPane.refreshTables();
            try {
                Singleton.getInstance().getCodeArea().setText(XMLUtils.getXml(Canvas.getCanvas()));
            } catch (JAXBException e1) {
                e1.printStackTrace();
            }
        });
        toolMenu.add(clear);
        menuBar.add(fileMenu);
        menuBar.add(codeMenu);
        menuBar.add(shapeMenu);
        menuBar.add(toolMenu);
        setJMenuBar(menuBar);

        // Zakázání menu tlačítek
        enableDisableMenuButtons(false);

        // Nastavení okna
        setVisible(true);
        setMinimumSize(new Dimension(1024, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);

        Singleton.getInstance().setMaxedWindowSize(getSize());
    }

    public JEditSplitPane getEditSplitPane() {
        return editSplitPane;
    }

    private void addMainSplitPane() {
        // Kód SVG
        RSyntaxTextArea codeArea = new RSyntaxTextArea();
        // Document listener pro kód
        DocumentListener documentListener = new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (Singleton.getInstance().getListen()) {
                    try {
                        Canvas canvas = XMLUtils.getCanvas(codeArea.getText());
                        Singleton.getInstance().setShapes(canvas.getShapes());
                        editSplitPane.refreshTables();
                        Singleton.getInstance().getDrawPanel().repaint();
                    } catch (JAXBException e1) {
                        // e1.printStackTrace();
                    }
                }
            }
        };
        codeArea.getDocument().addDocumentListener(documentListener);
        codeArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_XML);
        RTextScrollPane codeAreaScroll = new RTextScrollPane(codeArea);
        Singleton.getInstance().setCodeArea(codeArea);
        // Pravý JSplitPanel pro editaci
        this.editSplitPane = new JEditSplitPane();
        // Inicializace DrawPanelu, přidání tvarů
        Singleton.getInstance().setDrawPanel(new JDrawPanel(editSplitPane));
        // Vytvoření a konfigurace TabbedPane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Kód", codeAreaScroll);
        tabbedPane.addTab("Náhled", Singleton.getInstance().getDrawPanel());
        // Vytvoření SplitPane s rozdělením
        this.mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tabbedPane, this.editSplitPane);
        this.mainSplitPane.setResizeWeight(0.9); // Rozdělení 90% pro drawPanel, 10% pro rightSplitPane
        this.mainSplitPane.setDividerSize(3);
        // Přidání TabbedPane do JFrame
        add(this.mainSplitPane);
        revalidate();
        repaint();

        Singleton.getInstance().setListen(true);
    }

    private void enableDisableMenuButtons(boolean enable) {
        saveAsMenuItem.setEnabled(enable);
        codeMenu.setEnabled(enable);
        shapeMenu.setEnabled(enable);
        toolMenu.setEnabled(enable);
    }

    private void openSVG() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Otevřít SVG soubor");
        fileChooser.setFileFilter(new FileNameExtensionFilter("SVG soubory", "svg"));
        int userSelection = fileChooser.showOpenDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                reader.close();

                // znovupřidání
                Canvas canvas = XMLUtils.getCanvas(content.toString());
                Singleton.getInstance().setShapes(canvas.getShapes());
                addMainSplitPane();
                enableDisableMenuButtons(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        // znovupřidání
        addMainSplitPane();
        enableDisableMenuButtons(true);
    }
}
