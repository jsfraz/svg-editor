package cz.josefraz;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.table.AbstractTableModel;

import cz.josefraz.shapes.Shape;

// Vlastní model tabulky odvozený od AbstractTableModel
public class ShapeTableModel extends AbstractTableModel {

    private ArrayList<Shape> shapes;

    public ShapeTableModel(Shape... shapes) {
        this.shapes = new ArrayList<>(Arrays.asList(shapes));
    }

    @Override
    public int getRowCount() {
        return shapes.size();
    }

    @Override
    public int getColumnCount() {
        return 2; // Počet sloupců - index a název tvaru
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Shape shape = shapes.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex; // Index
            case 1:
                return String.format("%s%s", shape.getShapeName().toLowerCase(), getShapeNumber(rowIndex, shape.getClass())); // Název tvaru
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Index";
            case 1:
                return "Shape Name";
            default:
                throw new ArrayIndexOutOfBoundsException();
        }
    }

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