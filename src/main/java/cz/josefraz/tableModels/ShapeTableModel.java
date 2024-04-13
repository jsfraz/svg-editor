package cz.josefraz.tableModels;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import cz.josefraz.shapes.Shape;
import cz.josefraz.utils.Singleton;

// Vlastní model tabulky odvozený od AbstractTableModel
public class ShapeTableModel extends AbstractTableModel {

    private ArrayList<Shape> shapes;

    public ShapeTableModel() {
        this.shapes = Singleton.GetInstance().getShapes();
    }

    public void refershShapes() {
        this.shapes = Singleton.GetInstance().getShapes();
    }

    @Override
    public int getRowCount() {
        return shapes.size();
    }

    @Override
    public int getColumnCount() {
        return 1; // Počet sloupců
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Shape shape = shapes.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return String.format("%s%s", shape.getShapeName().toLowerCase(), getShapeNumber(rowIndex, shape.getClass())); // Název tvaru
            default:
                throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Shape Name";
            default:
                throw new ArrayIndexOutOfBoundsException();
        }
    }

    // Vrátí tvar a jeho index
    private int getShapeNumber(int rowIndex, Class<?> shapeClass) {
        int number = 0;
        for (int i = 0; i < rowIndex - 1; i++) {
            if (shapes.get(i).getClass().equals(shapeClass)) {
                number++;
            }
        }
        return number;
    }
}