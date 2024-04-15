package cz.josefraz.utils;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class SVGUtils {

    // Parsování SVG
    public static Document parseSVG(String svgString) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(svgString));
        return builder.parse(is);
    }

    // Odstranění mezer a odsazení ze SVG řetězce
    public static String optimizeSVG(String svgString) {
        return svgString.replaceAll(">\\s+<", "><");
    }

    // Zkrásnění SVG
    public static String beautifySVG(String svgString) throws Exception {
        // Zpracování SVG řetězce do DOM dokumentu
        Document document = parseSVG(svgString);
        // Vytvoření transformátoru
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty("indent", "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        transformer.setOutputProperty("omit-xml-declaration", "yes"); // Vynechat XML deklaraci
        // Vylepšení DOM dokumentu
        StringWriter stringWriter = new StringWriter();
        transformer.transform(new DOMSource(document), new StreamResult(stringWriter));
        // Vrácení vylepšeného SVG řetězce
        return stringWriter.toString();
    }
}
