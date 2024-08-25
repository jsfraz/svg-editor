package cz.josefraz;

import com.formdev.flatlaf.FlatLightLaf;

import cz.josefraz.frames.SVGEditor;

public class Main {
    public static void main(String[] args) {
        FlatLightLaf.setup();
        new SVGEditor();
    }
}