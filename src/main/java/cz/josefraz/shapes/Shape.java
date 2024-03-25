package cz.josefraz.shapes;

public abstract class Shape {

    private int positionX, postitionY;
    private String color;

    public Shape(int x, int y, String color) {
        this.positionX = x;
        this.postitionY = y;
        this.color = color;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPostitionY() {
        return postitionY;
    }

    public String getColor() {
        return color;
    }

    public void setPositionX(int x) {
        this.positionX = x;
    }

    public void setPostitionY(int y) {
        this.postitionY = y;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getShapeName() {
        return this.getClass().getSimpleName();
    }
}
