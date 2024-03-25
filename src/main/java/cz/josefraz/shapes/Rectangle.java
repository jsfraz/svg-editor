package cz.josefraz.shapes;

public class Rectangle extends Shape {
    
    private int lengthA, lengthB;

    public Rectangle(int x, int y, String color, int lengthA, int lengthB) {
        super(x, y, color);
        this.lengthA = lengthA;
        this.lengthB = lengthB;
    }

    public int getLengthA() {
        return lengthA;
    }

    public int getLengthB() {
        return lengthB;
    }

    public void setLengthA(int lengthA) {
        this.lengthA = lengthA;
    }

    public void setLengthB(int lengthB) {
        this.lengthB = lengthB;
    }
}
