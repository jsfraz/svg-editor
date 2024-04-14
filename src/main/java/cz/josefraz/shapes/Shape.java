package cz.josefraz.shapes;

import java.awt.Color;

public abstract class Shape implements IShape {

    private int positionX, postitionY;
    private String borderColor, fillColor;
    private float strokeWidth;

    public Shape(int positionX, int postitionY, String borderColor, String fillColor, float strokeWidth) {
        this.positionX = positionX;
        this.postitionY = postitionY;
        this.borderColor = borderColor;
        try {
            Color.decode(this.borderColor);
        } catch (Exception e) {
            this.borderColor = "#00000";
            e.printStackTrace();
        }
        this.fillColor = fillColor;
        try {
            Color.decode(this.fillColor);
        } catch (Exception e) {
            this.fillColor = "#FFFFFF";
            e.printStackTrace();
        }
        this.strokeWidth = strokeWidth;
    }

    public int getPositionX() {
        return positionX;
    }

    public float getStrokeWidth() {
        return strokeWidth;
    }

    public int getPostitionY() {
        return postitionY;
    }

    public String getborderColor() {
        return borderColor;
    }

    public String getShapeName() {
        return this.getClass().getSimpleName();
    }

    public String getFillColor() {
        return fillColor;
    }
}
