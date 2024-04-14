package cz.josefraz.utils;

import java.util.ArrayList;

import cz.josefraz.shapes.Shape;

public class Singleton {
    private static Singleton instance;
    private ArrayList<Shape> shapes;

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
}