package cz.josefraz.tableModels;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import cz.josefraz.shapes.Line;
import cz.josefraz.utils.Singleton;

// Vlastní model tabulky odvozený od AbstractTableModel
public class AttributeTableModel extends AbstractTableModel {

    private int shapeIndex = -1;
    private HashMap<String, Field> attributes = new HashMap<>();

    // Nastavení dat tabulky podle tvaru
    public void setAttributes(int shapeIndex) {
        this.shapeIndex = shapeIndex;
        this.attributes = new HashMap<>();

        if (shapeIndex != -1) {
            // Získání třídy objektu
            Class<?> shapeClass = Singleton.GetInstance().getShapes().get(shapeIndex).getClass();

            // Získání všech atributů třídy včetně zděděných atributů
            while (shapeClass != null) {
                Field[] shapeAttributes = shapeClass.getDeclaredFields();

                // Procházení všech atributů třídy
                for (Field field : shapeAttributes) {
                    // Nastavení přístupu k privátním atributům
                    field.setAccessible(true);
                    attributes.put(field.getName(), field);
                }

                // Přesun na nadřazenou třídu pro získání jejích atributů
                shapeClass = shapeClass.getSuperclass();
            }

            // Výjimka fillColor pro Line
            if (Singleton.GetInstance().getShapes().get(shapeIndex).getClass() == Line.class) {
                attributes.remove("fillColor");
            }
        }

        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return attributes.size();
    }

    @Override
    public int getColumnCount() {
        return 2; // Počet sloupců - index a název tvaru
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // Získání vstupu podle indexu
        @SuppressWarnings("unchecked")
        Map.Entry<String, Field> entry = this.attributes.entrySet().toArray(new Map.Entry[0])[rowIndex];
        switch (columnIndex) {
            case 0:
                return entry.getKey(); // Jméno atributu
            case 1:
                try {
                    return entry.getValue().get(Singleton.GetInstance().getShapes().get(shapeIndex));
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                } // Hodnota atributu
            default:
                throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        if (columnIndex == 1) {
            // Získání třídy objektu
            Class<?> shapeClass = Singleton.GetInstance().getShapes().get(shapeIndex).getClass();
            while (shapeClass != null) {
                try {
                    Field field = shapeClass.getDeclaredField((String) getValueAt(rowIndex, 0));
                    // Nastavení přístupnosti atributu, pokud je soukromý
                    field.setAccessible(true);
                    // Nastavení nové hodnoty atributu
                    Object newValue = null;
                    // Přetypování na správný typ
                    switch (field.getType().getSimpleName()) {
                        case "int":
                            newValue = Integer.parseInt((String) value);
                            break;

                        case "float":
                            newValue = Float.valueOf((String) value);
                            break;

                        case "String":
                            newValue = (String) value;
                            break;
                    }
                    field.set(Singleton.GetInstance().getShapes().get(shapeIndex), newValue);
                } catch (Exception e) {
                    // e.printStackTrace();
                }
                // Přesun na nadřazenou třídu pro získání jejích atributů
                shapeClass = shapeClass.getSuperclass();
            }
            // Update
            fireTableCellUpdated(rowIndex, columnIndex);
            Singleton.GetInstance().getDrawPanel().repaint();
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return false;
        } else {
            return true;
        }
    }
}