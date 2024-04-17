package cz.josefraz.utils;

import java.awt.Color;

public class ColorUtils {
    
    public static String colorToHex(Color color) {
        return String.format("#%02X%02X%02X", color.getRed(), color.getGreen(), color.getBlue());
    }
}
