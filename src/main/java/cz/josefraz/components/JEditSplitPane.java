package cz.josefraz.components;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.bind.JAXBException;

import cz.josefraz.shapes.Canvas;
import cz.josefraz.tableModels.AttributeTableModel;
import cz.josefraz.tableModels.ShapeTableModel;
import cz.josefraz.utils.Singleton;
import cz.josefraz.utils.XMLUtils;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JEditSplitPane extends JSplitPane {

    // Tabulka tvarů
    private JLabel shapeLabel;
    private JTable shapeTable;
    private ShapeTableModel shapeModel;
    // Tabulka atributů tvaru
    private JLabel attributeLabel;
    private JTable attributeTable;
    private AttributeTableModel attributeModel;

    public JEditSplitPane() {
        // Vertikální nastavení
        setOrientation(JSplitPane.VERTICAL_SPLIT);
        setResizeWeight(0.7); // Rozdělení 70% a 30%
        setDividerSize(3);

        // Tvary
        JPanel shapesPanel = new JPanel(new BorderLayout());
        // Vytvoření popisku
        shapeLabel = new JLabel();
        shapeLabel.setFont(shapeLabel.getFont().deriveFont(15f)); // Zvětšení velikosti písma
        // Vytvoření modelu tabulky pro tvary
        this.shapeModel = new ShapeTableModel();
        // Vytvoření tabulky s tvary
        this.shapeTable = new JTable(this.shapeModel);
        this.shapeTable.setFont(this.shapeTable.getFont().deriveFont(14f));
        // Nezobrazovat záhlaví
        this.shapeTable.setTableHeader(null);
        // Event pro vybírání hodnot z tabulky
        ListSelectionModel shapeSelectionModel = this.shapeTable.getSelectionModel();
        shapeSelectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                // FIXME když se edituej atribut a překlikne se na jiný tvar, hodnota v buňce zůstane, ALE neprojde to
                attributeLabel.setText(String.format("Atributy (%s)", attributeTable.getModel().getRowCount()));
                // Kontrola jestli je vybrán právě jeden index
                if (shapeSelectionModel.getSelectedItemsCount() == 1) {
                    // Změna tabulky atributů
                    attributeModel.setAttributes(shapeTable.getSelectedRow());
                    attributeModel.fireTableDataChanged();
                } else {
                    attributeModel.setAttributes(-1);
                }
            }
        });
        // Přidání posluchače klávesnice
        this.shapeTable.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_DELETE) {
                    int[] selectedRows = shapeTable.getSelectedRows();
                    if (selectedRows.length > 0) { // Pokud jsou vybrány nějaké řádky
                        // Projít vybrané řádky v opačném pořadí (abychom se vyhnuli problémům s indexy)
                        for (int i = selectedRows.length - 1; i >= 0; i--) {
                            Singleton.getInstance().removeShapeByIndex(selectedRows[i]);
                        }
                        shapeModel.fireTableDataChanged();
                        attributeModel.setAttributes(-1);
                        attributeModel.fireTableDataChanged();
                        Singleton.getInstance().getDrawPanel().repaint();
                        try {
                            Singleton.getInstance().getCodeArea().setText(XMLUtils.getXml(Canvas.getCanvas()));
                        } catch (JAXBException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        });
        // Vytvoření JScrollPane pro tabulku
        JScrollPane shapeScrollPane = new JScrollPane(this.shapeTable);
        shapeScrollPane.setPreferredSize(this.shapeTable.getPreferredSize());
        // Přidání popisku a JScrollPane s tabulkou do panelu
        shapesPanel.add(shapeLabel, BorderLayout.NORTH);
        shapesPanel.add(shapeScrollPane, BorderLayout.CENTER);
        // Přidání panelu do hlavního kontejneru
        setTopComponent(shapesPanel); // První komponenta (nahoru)

        // Atributy
        JPanel attributesPanel = new JPanel(new BorderLayout());
        // Vytvoření modelu tabulky pro atributy
        this.attributeModel = new AttributeTableModel();
        // Vytvoření tabulky s atributy
        this.attributeTable = new JTable(this.attributeModel);
        this.attributeTable.setFont(this.attributeTable.getFont().deriveFont(14f));
        // Nezobrazovat záhlaví
        this.attributeTable.setTableHeader(null);
        // Vytvoření popisku
        attributeLabel = new JLabel(String.format("Atributy (%s)", attributeTable.getModel().getRowCount()));
        attributeLabel.setFont(attributeLabel.getFont().deriveFont(15f)); // Zvětšení velikosti písma
        // Vytvoření JScrollPane pro tabulku
        JScrollPane attributeScrollPane = new JScrollPane(this.attributeTable);
        attributeScrollPane.setPreferredSize(this.attributeTable.getPreferredSize());
        // Přidání popisku a JScrollPane s tabulkou do panelu
        attributesPanel.add(attributeLabel, BorderLayout.NORTH);
        attributesPanel.add(attributeScrollPane, BorderLayout.CENTER);
        // Přidání panelu do hlavního kontejneru
        setBottomComponent(attributesPanel); // Druhá komponenta (dolů)

        refreshTables();
    }

    // Refresh tabulek po přidání tvaru
    public void refreshTables() {
        this.shapeModel.refreshShapes();
        this.shapeLabel.setText(String.format("Tvary (%s)", this.shapeTable.getModel().getRowCount()));
    }
}
