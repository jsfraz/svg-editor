package cz.josefraz.shapes;

import java.awt.Color;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Shape implements IShape {

    @XmlAttribute(name="x")
    private int positionX;
    @XmlAttribute(name="y")
    private int postitionY;
    @XmlAttribute(name="stroke")
    private String strokeColor;
    @XmlAttribute(name="fill")
    private String fillColor;
    @XmlAttribute(name="stroke-width")
    private float strokeWidth;

    public Shape(int positionX, int postitionY, String borderColor, String fillColor, float strokeWidth) {
        this.positionX = positionX;
        this.postitionY = postitionY;
        this.strokeColor = borderColor;
        if (this.strokeColor != null) {
            this.strokeColor = this.strokeColor.toUpperCase();
        }
        try {
            Color.decode(this.strokeColor);
        } catch (Exception e) {
            this.strokeColor = "#00000";
            // e.printStackTrace();
        }
        this.fillColor = fillColor;
        if (!getClass().equals(Line.class)) {
            try {
                Color.decode(this.fillColor);
            } catch (Exception e) {
                this.fillColor = "#FFFFFF";
                // e.printStackTrace();
            }
        }
        if (this.fillColor != null) {
            this.fillColor = this.fillColor.toUpperCase();
        }
        this.strokeWidth = strokeWidth;
    }

    public int getPositionX() {
        return positionX;
    }

    public float getStrokeWidth() {
        return strokeWidth;
    }

    public int getPositionY() {
        return postitionY;
    }

    public String getborderColor() {
        return strokeColor;
    }

    public String getShapeName() {
        return getClass().getSimpleName();
    }

    public String getFillColor() {
        return fillColor;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int postitionY) {
        this.postitionY = postitionY;
    }

    public void setStrokeColor(String borderColor) {
        this.strokeColor = borderColor;
    }

    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }

    public void setStrokeWidth(float strokeWidth) {
        this.strokeWidth = strokeWidth;
    }
}
