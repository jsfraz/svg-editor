package cz.josefraz.shapes;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cz.josefraz.utils.Singleton;

@XmlRootElement(name = "svg")
@XmlSeeAlso({ Ellipse.class, Circle.class, Square.class, Line.class, Rectangle.class })
public class Canvas {

    private ArrayList<Shape> shapes = new ArrayList<>();

    @XmlAttribute()
    private String width = "%s";
    @XmlAttribute()
    private String height = "%s";

    public Canvas() {
        Dimension size = Singleton.getInstance().getMaxedWindowSize();
        width = String.format(width, (int) size.getWidth());
        height = String.format(height, (int) size.getHeight());
    }

    @XmlElementWrapper(name = "g")
    @XmlAnyElement(lax = true)
    public ArrayList<Shape> getShapes() {
        return shapes;
    }

    public static Canvas getCanvas() {
        Canvas image = new Canvas();
        image.shapes = Singleton.getInstance().getShapes();
        return image;
    }

    public String toJson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        return gson.toJson(this);
    }
}