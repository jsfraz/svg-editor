package cz.josefraz.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class FileUtils {

    /*
     * Vrátí obsah textového souboru z resources složky.
     * https://stackoverflow.com/questions/15749192/how-do-i-load-a-file-from-
     * https://www.baeldung.com/convert-input-stream-to-string
     */
    public static String readTextFile(String file) {
        try {
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(file);
            StringBuilder textBuilder = new StringBuilder();
            try (Reader reader = new BufferedReader(
                    new InputStreamReader(inputStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
                int c = 0;
                while ((c = reader.read()) != -1) {
                    textBuilder.append((char) c);
                }
            }
            return textBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
