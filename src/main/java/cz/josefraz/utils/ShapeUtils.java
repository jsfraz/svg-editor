package cz.josefraz.utils;

import java.util.ArrayList;
import java.util.Random;
import cz.josefraz.shapes.*;

public class ShapeUtils {

    // TODO souřadnice
    public static ArrayList<Shape> generateRandomShapes(int count, int sizeX, int sizeY) {
        Random random = new Random();
        ArrayList<Shape> randomShapes = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            int shapeType = random.nextInt(5);
            switch (shapeType) {
                case 0:
                    randomShapes.add(createRandomCircle(sizeX, sizeY));
                    break;
                case 1:
                    randomShapes.add(createRandomRectangle(sizeX, sizeY));
                    break;
                case 2:
                    randomShapes.add(createRandomSquare(sizeX, sizeY));
                    break;
                case 3:
                    randomShapes.add(createRandomEllipse(sizeX, sizeY));
                    break;
                case 4:
                    randomShapes.add(createRandomLine(sizeX, sizeY));
                    break;
            }
        }

        return randomShapes;
    }

    public static Shape generateRandomShape(int sizeX, int sizeY) {
        Shape shape = null;
        Random random = new Random();
        int shapeType = random.nextInt(5);
        switch (shapeType) {
            case 0:
                shape = createRandomCircle(sizeX, sizeY);
                break;
            case 1:
                shape = createRandomRectangle(sizeX, sizeY);
                break;
            case 2:
                shape = createRandomSquare(sizeX, sizeY);
                break;
            case 3:
                shape = createRandomEllipse(sizeX, sizeY);
                break;
            case 4:
                shape = createRandomLine(sizeX, sizeY);
                break;
        }
        return shape;
    }

    public static Shape generateRandomShape() {
        return generateRandomShape(1, 1);
    }

    public static Circle createRandomCircle(int sizeX, int sizeY) {
        Random random = new Random();
        int radius = random.nextInt(100) + 1;
        int x = random.nextInt(sizeX) + 1;
        int y = random.nextInt(sizeY) + 1;
        String fillColor = generateRandomColor();
        String borderColor = generateRandomColor();
        float strokeWidth = random.nextFloat() * 10;

        return new Circle(x, y, borderColor, fillColor, radius, strokeWidth);
    }

    public static Rectangle createRandomRectangle(int sizeX, int sizeY) {
        Random random = new Random();
        int width = random.nextInt(100) + 1;
        int height = random.nextInt(100) + 1;
        int x = random.nextInt(sizeX) + 1;
        int y = random.nextInt(sizeY) + 1;
        String fillColor = generateRandomColor();
        String borderColor = generateRandomColor();
        float strokeWidth = random.nextFloat() * 10;

        return new Rectangle(x, y, borderColor, fillColor, width, height, strokeWidth);
    }

    public static Square createRandomSquare(int sizeX, int sizeY) {
        Random random = new Random();
        int side = random.nextInt(100) + 1;
        int x = random.nextInt(sizeX) + 1;
        int y = random.nextInt(sizeY) + 1;
        String fillColor = generateRandomColor();
        String borderColor = generateRandomColor();
        float strokeWidth = random.nextFloat() * 10;

        return new Square(x, y, borderColor, fillColor, side, strokeWidth);
    }

    public static Ellipse createRandomEllipse(int sizeX, int sizeY) {
        Random random = new Random();
        int width = random.nextInt(100) + 1;
        int height = random.nextInt(100) + 1;
        int x = random.nextInt(sizeX) + 1;
        int y = random.nextInt(sizeY) + 1;
        String fillColor = generateRandomColor();
        String borderColor = generateRandomColor();
        float strokeWidth = random.nextFloat() * 10;

        return new Ellipse(x, y, borderColor, fillColor, width, height, strokeWidth);
    }

    public static Line createRandomLine(int sizeX, int sizeY) {
        Random random = new Random();
        int x1 = random.nextInt(sizeX) + 1;
        int y1 = random.nextInt(sizeY) + 1;
        int x2 = random.nextInt(sizeX) + 1;
        int y2 = random.nextInt(sizeY) + 1;
        String borderColor = generateRandomColor();
        float strokeWidth = random.nextFloat() * 10;

        return new Line(x1, y1, x2, y2, borderColor, strokeWidth);
    }

    public static String generateRandomColor() {
        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return String.format("#%02x%02x%02x", r, g, b);
    }
}
