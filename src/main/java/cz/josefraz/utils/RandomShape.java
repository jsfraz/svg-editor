package cz.josefraz.utils;

import java.util.ArrayList;
import java.util.Random;
import cz.josefraz.shapes.*;

public class RandomShape {

    // TODO souřadnice
    public static ArrayList<Shape> generateRandomShapes(int count) {
        Random random = new Random();
        ArrayList<Shape> shapes = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            int shapeType = random.nextInt(5);
            switch (shapeType) {
                case 0:
                    shapes.add(createRandomCircle(random));
                    break;
                case 1:
                    shapes.add(createRandomRectangle(random));
                    break;
                case 2:
                    shapes.add(createRandomSquare(random));
                    break;
                case 3:
                    shapes.add(createRandomOval(random));
                    break;
                case 4:
                    shapes.add(createRandomLine(random));
                    break;
            }
        }

        return shapes;
    }

    private static Circle createRandomCircle(Random random) {
        int radius = random.nextInt(100) + 1;
        int x = random.nextInt(1000) + 1;
        int y = random.nextInt(1000) + 1;
        String fillColor = generateRandomColor(random);
        String borderColor = generateRandomColor(random);
        float thickness = random.nextFloat() * 10;

        return new Circle(x, y, borderColor, fillColor, radius, thickness);
    }

    private static Rectangle createRandomRectangle(Random random) {
        int width = random.nextInt(100) + 1;
        int height = random.nextInt(100) + 1;
        int x = random.nextInt(1000) + 1;
        int y = random.nextInt(1000) + 1;
        String fillColor = generateRandomColor(random);
        String borderColor = generateRandomColor(random);
        float thickness = random.nextFloat() * 10;

        return new Rectangle(x, y, borderColor, fillColor, width, height, thickness);
    }

    private static Square createRandomSquare(Random random) {
        int side = random.nextInt(100) + 1;
        int x = random.nextInt(1000) + 1;
        int y = random.nextInt(1000) + 1;
        String fillColor = generateRandomColor(random);
        String borderColor = generateRandomColor(random);
        float thickness = random.nextFloat() * 10;

        return new Square(x, y, borderColor, fillColor, side, thickness);
    }

    private static Oval createRandomOval(Random random) {
        int width = random.nextInt(100) + 1;
        int height = random.nextInt(100) + 1;
        int x = random.nextInt(1000) + 1;
        int y = random.nextInt(1000) + 1;
        String fillColor = generateRandomColor(random);
        String borderColor = generateRandomColor(random);
        float thickness = random.nextFloat() * 10;

        return new Oval(x, y, borderColor, fillColor, width, height, thickness);
    }

    private static Line createRandomLine(Random random) {
        int x1 = random.nextInt(1000) + 1;
        int y1 = random.nextInt(1000) + 1;
        int x2 = random.nextInt(1000) + 1;
        int y2 = random.nextInt(1000) + 1;
        String borderColor = generateRandomColor(random);
        float thickness = random.nextFloat() * 10;

        return new Line(x1, y1, borderColor, x2, y2, thickness);
    }

    private static String generateRandomColor(Random random) {
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return String.format("#%02x%02x%02x", r, g, b);
    }
}
