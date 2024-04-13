package cz.josefraz.components;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import cz.josefraz.tableModels.AttributeTableModel;
import cz.josefraz.tableModels.ShapeTableModel;
import cz.josefraz.utils.Singleton;

import java.awt.BorderLayout;

public class JEditSplitPane extends JSplitPane {

    // Tabulka tvarů
    private JTable shapeTable;
    private ShapeTableModel shapeModel;
    // Tabulka atributů tvaru
    private JTable attributeTable;
    private AttributeTableModel attributeModel;

    public JEditSplitPane() {
        // Vertikální nastavení
        setOrientation(JSplitPane.VERTICAL_SPLIT);
        setResizeWeight(0.7); // Rozdělení 70% a 30%
        setDividerSize(3);

        // Tvary
        JPanel shapesPanel = new JPanel(new BorderLayout());
        JLabel shapesLabel = new JLabel("Tvary");
        shapesLabel.setFont(shapesLabel.getFont().deriveFont(15f)); // Zvětšení velikosti písma
        shapesPanel.add(shapesLabel, BorderLayout.NORTH);
        // Vytvoření modelu tabulky pro tvary
        this.shapeModel = new ShapeTableModel();
        // Vytvoření tabulky s tvary
        this.shapeTable = new JTable(this.shapeModel);
        this.shapeTable.setFont(this.shapeTable.getFont().deriveFont(14f));
        // Event pro vybírání hodnot z tabulky
        ListSelectionModel shapeSelectionModel = this.shapeTable.getSelectionModel();
        shapeSelectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                // Kontrola jestli je vybrán právě jeden index
                if (shapeSelectionModel.getSelectedItemsCount() == 1) {
                    // Změna tabulky atributů
                    attributeModel.setAttributes(Singleton.GetInstance().getShapes().get(shapeTable.getSelectedRow()));
                    attributeModel.fireTableDataChanged();
                } else {
                    attributeModel.setAttributes(null);
                }
            }
        });
        shapesPanel.add(shapeTable);
        setTopComponent(shapesPanel); // První komponenta (nahoru)

        // Atributy
        JPanel attributesPanel = new JPanel(new BorderLayout());
        JLabel attributesLabel = new JLabel("Atributy");
        attributesLabel.setFont(attributesLabel.getFont().deriveFont(15f)); // Zvětšení velikosti písma
        attributesPanel.add(attributesLabel, BorderLayout.NORTH);
        // Vytvoření modelu tabulky pro atributy
        this.attributeModel = new AttributeTableModel();        // TODO change index
        // Vytvoření tabulky s atributy
        this.attributeTable = new JTable(this.attributeModel);
        this.attributeTable.setFont(this.attributeTable.getFont().deriveFont(14f));
        attributesPanel.add(attributeTable);
        setBottomComponent(attributesPanel); // Druhá komponenta (dolů)

        // TODO scroll tabulek
    }

    // Refresh tabulek po přidání tvaru
    public void refreshTables() {
        this.shapeModel.refreshShapes();
    }
}
