package cz.josefraz.shapes;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import cz.josefraz.utils.Singleton;

@XmlRootElement(name = "svg")
@XmlSeeAlso({Ellipse.class, Circle.class, Square.class, Line.class, Rectangle.class})
public class Canvas {
    
    private List<Shape> shapes = new ArrayList<>();

    @XmlAttribute()
    private String width = "%s";
    @XmlAttribute()
    private String height = "%s";

    public Canvas() {
        Dimension size = Singleton.GetInstance().getMaxedWindowSize();
        width = String.format(width, (int) size.getWidth());
        height = String.format(height, (int) size.getHeight());
    }

    @XmlElementWrapper(name = "g")
    @XmlAnyElement(lax=true)
    public List<Shape> getShapes()
    {
        return shapes;
    }

    public static Canvas getImage() {
        Canvas image = new Canvas();
        image.shapes = Singleton.GetInstance().getShapes();
        return image;
    }
}