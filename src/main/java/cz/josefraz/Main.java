package cz.josefraz;

import java.util.ArrayList;
import java.util.Arrays;

import cz.josefraz.frames.SVGEditor;
import cz.josefraz.shapes.*;
import cz.josefraz.utils.Singleton;

public class Main {
    public static void main(String[] args) {
        // TODO odstranit
        Shape[] shapes = {
                new Circle(50, 30, "#0000FF", "#ff8503", 20, 1),
                new Rectangle(20, 60, "#240771", "#fedf00", 80, 40, 2),
                new Square(80, 90, "#8b4513", "#6a329f", 40, 3),
                new Oval(150, 250, "#274e13", "#cc0000", 60, 90, 2.5f),
                new Line(150, 100, "#a64d79", 400, 300, 15f)
        };
        Singleton.GetInstance().setShapes(new ArrayList<>(Arrays.asList(shapes)));

        new SVGEditor();
    }
}