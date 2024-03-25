package cz.josefraz;

public class Circle extends Shape {

    private int diameter;

    public Circle(int x, int y, String color, int diameter) {
        super(x, y, color);
        this.diameter = diameter;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }
}
