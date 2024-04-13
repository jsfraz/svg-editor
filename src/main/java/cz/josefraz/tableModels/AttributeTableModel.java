package cz.josefraz.tableModels;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.AbstractTableModel;

import cz.josefraz.shapes.Shape;

// Vlastní model tabulky odvozený od AbstractTableModel
public class AttributeTableModel extends AbstractTableModel {

    private HashMap<String,Object> attributes = new HashMap<>();

    public void setAttributes(Shape shape) {
        // Získání třídy objektu
        Class<?> shapeClass = shape.getClass();

        // Získání všech atributů třídy včetně zděděných atributů
        while (shapeClass != null) {
            Field[] shapeAttributes = shapeClass.getDeclaredFields();

            // Procházení všech atributů třídy
            for (Field attribute : shapeAttributes) {
                try {
                    // Nastavení přístupu k privátním atributům
                    attribute.setAccessible(true);

                    // Získání hodnoty atributu
                    Object value = attribute.get(shape);

                    // Uložení názvu atributu a jeho hodnoty do HashMap
                    attributes.put(attribute.getName(), value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            // Přesun na nadřazenou třídu pro získání jejích atributů
            shapeClass = shapeClass.getSuperclass();
        }
    }

    @Override
    public int getRowCount() {
        return attributes.size();
    }

    @Override
    public int getColumnCount() {
        return 2; // Počet sloupců - index a název tvaru
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // Získání vstupu podle indexu
        Map.Entry<String, Object> entry = this.attributes.entrySet().toArray(new Map.Entry[0])[rowIndex];
        switch (columnIndex) {
            case 0:
                return entry.getKey();        // Jméno atributu
            case 1:
                return entry.getValue();     // Hodnota atributu
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Atribut";
            case 1:
                return "Hodnota";
            default:
                throw new ArrayIndexOutOfBoundsException();
        }
    }
}