package cz.josefraz.utils;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import cz.josefraz.shapes.Canvas;

public class XMLUtils {

    public static String getXml(Canvas image) throws JAXBException {
        JAXBContext ctx = null;
        ctx = JAXBContext.newInstance(Canvas.class);
        Marshaller marshaller = ctx.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        // Nastavení, aby se nevytvořila XML hlavička
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        StringWriter sw = new StringWriter();
        marshaller.marshal(image, sw);
        String xmlString = sw.toString();
        return xmlString;
    }

    public static Canvas getCanvas(String xml) throws JAXBException {
        JAXBContext ctx = JAXBContext.newInstance(Canvas.class);
        Unmarshaller unmarshaller = ctx.createUnmarshaller();
        StringReader sr = new StringReader(xml);
        Canvas svgImage = (Canvas) unmarshaller.unmarshal(sr);
        return svgImage;
    }
}
