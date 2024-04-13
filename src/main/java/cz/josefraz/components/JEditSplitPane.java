package cz.josefraz.components;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;

import cz.josefraz.tableModels.AttributeTableModel;
import cz.josefraz.tableModels.ShapeTableModel;

import java.awt.BorderLayout;

public class JEditSplitPane extends JSplitPane {

    // Tabulka tvarů
    private JTable shapeTable;
    // Tabulka atributů tvaru
    private JTable attributeTable;

    public JEditSplitPane() {
        // Vertikální nastavení
        setOrientation(JSplitPane.VERTICAL_SPLIT);
        setResizeWeight(0.5); // Rozdělení 50% pro každou část
        setDividerSize(3);

        // TODO velikost fontu tabulek, šířka sloupců, horizontální zarovnání sloupců

        // Tvary
        JPanel shapesPanel = new JPanel(new BorderLayout());
        JLabel shapesLabel = new JLabel("Tvary");
        shapesLabel.setFont(shapesLabel.getFont().deriveFont(15f)); // Zvětšení velikosti písma
        shapesPanel.add(shapesLabel, BorderLayout.NORTH);
        // Vytvoření modelu tabulky pro tvary
        ShapeTableModel shapeModel = new ShapeTableModel();
        // Vytvoření tabulky s tvary
        this.shapeTable = new JTable(shapeModel);
        shapesPanel.add(shapeTable);
        shapesPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Odsazení od kraje
        setTopComponent(shapesPanel); // První komponenta (nahoru)

        // Atributy
        JPanel attributesPanel = new JPanel(new BorderLayout());
        JLabel attributesLabel = new JLabel("Atributy");
        attributesLabel.setFont(attributesLabel.getFont().deriveFont(15f)); // Zvětšení velikosti písma
        attributesPanel.add(attributesLabel, BorderLayout.NORTH);
        attributesPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Odsazení od kraje
        // Vytvoření modelu tabulky pro atributy
        AttributeTableModel attributeModel = new AttributeTableModel();        // TODO change index
        // Vytvoření tabulky s atributy
        this.attributeTable = new JTable(attributeModel);
        this.attributeTable.setFont(this.attributeTable.getFont().deriveFont(14.0f));
        attributesPanel.add(attributeTable);
        attributesPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Odsazení od kraje
        setBottomComponent(attributesPanel); // Druhá komponenta (dolů)
    }
}
