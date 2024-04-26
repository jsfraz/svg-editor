package cz.josefraz.utils;

import java.awt.Dimension;
import java.util.ArrayList;

import cz.josefraz.components.JDrawPanel;
import cz.josefraz.shapes.Shape;

public class Singleton {
    private static Singleton instance;
    private ArrayList<Shape> shapes;
    private JDrawPanel drawPanel;
    private Dimension maxedWindowSize;

    private Singleton() {
        this.shapes = new ArrayList<>();
    }
    
    public static Singleton GetInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public ArrayList<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(ArrayList<Shape> shapes) {
        this.shapes = shapes;
    }

    public void addShapes(ArrayList<Shape> shapes) {
        this.shapes.addAll(shapes);
    }

    public void addShape(Shape shape) {
        this.shapes.add(shape);
    }

    public JDrawPanel getDrawPanel() {
        return drawPanel;
    }

    public void setDrawPanel(JDrawPanel drawPanel) {
        this.drawPanel = drawPanel;
    }

    public Dimension getMaxedWindowSize() {
        return maxedWindowSize;
    }

    public void setMaxedWindowSize(Dimension maxedWindowSize) {
        this.maxedWindowSize = maxedWindowSize;
    }

    public void removeShapeByIndex(int index) {
        this.shapes.remove(index);
    }
}