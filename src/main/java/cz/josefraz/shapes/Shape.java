package cz.josefraz.shapes;

import java.awt.Color;

public abstract class Shape implements IShape {

    private int positionX, postitionY;
    private String outlineColor, fillColor;
    private float strokeWidth;

    public Shape(int positionX, int postitionY, String outlineColor, String fillColor, float strokeWidth) {
        // TODO ošetření čísel
        this.positionX = positionX;
        this.postitionY = postitionY;
        this.outlineColor = outlineColor;
        try {
            Color.decode(this.outlineColor);
        } catch (Exception e) {
            this.outlineColor = "#00000";
            e.printStackTrace();
        }
        this.fillColor = fillColor;
        try {
            Color.decode(this.fillColor);
        } catch (Exception e) {
            this.fillColor = "#FFFFFF";
            e.printStackTrace();
        }
        // TODO ošetření záporných čísel
        this.strokeWidth = strokeWidth;
    }

    public int getPositionX() {
        return positionX;
    }

    public float getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(float strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public int getPostitionY() {
        return postitionY;
    }

    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }

    public String getOutlineColor() {
        return outlineColor;
    }

    public void setPositionX(int x) {
        this.positionX = x;
    }

    public void setPostitionY(int y) {
        this.postitionY = y;
    }

    public void setOutlineColor(String color) {
        this.outlineColor = color;
    }

    public String getShapeName() {
        return this.getClass().getSimpleName();
    }

    public String getFillColor() {
        return fillColor;
    }
}
