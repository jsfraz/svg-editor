package cz.josefraz.shapes;

public class Square extends Shape {
    
    private int lengthA;

    public Square(int x, int y, String color, int lengthA) {
        super(x, y, color);
        this.lengthA = lengthA;
    }

    public int getLengthA() {
        return lengthA;
    }

    public void setLengthA(int lengthA) {
        this.lengthA = lengthA;
    }
}
