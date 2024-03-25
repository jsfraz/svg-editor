package cz.josefraz.shapes;

public class Line extends Shape {

    private int postitionX2, positionY2;

    public Line(int x, int y, String color, int postitionX2, int positionY2) {
        super(x, y, color);
        this.postitionX2 = postitionX2;
        this.positionY2 = positionY2;
    }

    public int getPostitionX2() {
        return postitionX2;
    }

    public int getPositionY2() {
        return positionY2;
    }

    public void setPostitionX2(int postitionX2) {
        this.postitionX2 = postitionX2;
    }

    public void setPositionY2(int positionY2) {
        this.positionY2 = positionY2;
    }
}
