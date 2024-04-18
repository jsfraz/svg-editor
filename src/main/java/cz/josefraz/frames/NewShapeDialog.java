package cz.josefraz.frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import cz.josefraz.shapes.Line;
import cz.josefraz.utils.ColorUtils;
import cz.josefraz.shapes.*;

public class NewShapeDialog extends JFrame {

    private Color fillColor = Color.white;
    private Color borderColor = Color.black;
    private float borderWidth = 1f;
    private JLabel fillColorSample, borderColorSample, borderWidthLabel;

    public NewShapeDialog(SVGEditor mainWindow, Shape shape) {
        super("Nový tvar");

        if (shape.getClass() != Line.class) {
            shape.setFillColor(ColorUtils.colorToHex(fillColor));
        }
        shape.setBorderColor(ColorUtils.colorToHex(borderColor));

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        // Odsazení zeshora
        contentPane.setBorder(new EmptyBorder(4, 0, 0, 0));

        if (shape.getClass() != Line.class) {
            JPanel fillPanel = new JPanel();
            fillPanel.setLayout(new BoxLayout(fillPanel, BoxLayout.X_AXIS));
            fillPanel.add(new JLabel("Barva výplně"));
            fillPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Mezera mezi textem a výběrem
            fillColorSample = new JLabel("          ");
            fillColorSample.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Zobrazit dialog pro výběr barvy
                    Color selectedColor = JColorChooser.showDialog(null, "Barva výplně", fillColor, false);
                    if (selectedColor != null) {
                        fillColor = selectedColor;
                        fillColorSample.setBackground(fillColor);
                    }
                }
            });
            fillColorSample.setOpaque(true);
            fillColorSample.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            fillColorSample.setBackground(fillColor);
            fillPanel.add(fillColorSample);
            contentPane.add(fillPanel);
            contentPane.add(Box.createVerticalStrut(10)); // Vertikální mezera mezi panely
        }

        JPanel borderPanel = new JPanel();
        borderPanel.setLayout(new BoxLayout(borderPanel, BoxLayout.X_AXIS));
        borderPanel.add(new JLabel("Barva okraje"));
        borderPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Mezera mezi textem a výběrem
        borderColorSample = new JLabel("          ");
        borderColorSample.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Zobrazit dialog pro výběr barvy
                Color selectedColor = JColorChooser.showDialog(null, "Barva okraje", borderColor, false);
                if (selectedColor != null) {
                    borderColor = selectedColor;
                    borderColorSample.setBackground(borderColor);
                }
            }
        });
        borderColorSample.setOpaque(true);
        borderColorSample.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        borderColorSample.setBackground(borderColor);
        borderPanel.add(borderColorSample);

        contentPane.add(borderPanel);
        contentPane.add(Box.createVerticalStrut(10)); // Vertikální mezera mezi panely

        // Slider pro šířku okraje
        borderWidthLabel = new JLabel(String.format("Šířka okraje: %s", borderWidth));
        JSlider borderWidthSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, (int) (borderWidth * 10));
        borderWidthSlider.setMajorTickSpacing(10);
        borderWidthSlider.setMinorTickSpacing(1);
        borderWidthSlider.setPaintTicks(true);
        borderWidthSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                borderWidth = (float) borderWidthSlider.getValue() / 10;
                borderWidthLabel.setText(String.format("Šířka okraje: %s", borderWidth));
            }
        });

        // Odsazení slideru od krajů
        borderWidthSlider.setBorder(new EmptyBorder(0, 10, 0, 10));

        contentPane.add(borderWidthLabel);
        contentPane.add(borderWidthSlider);

        // Horizontální vycentrování borderWidthLabel
        borderWidthLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        // Vertikální mezera mezi buttonPanelem a sliderem
        contentPane.add(Box.createVerticalStrut(5));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            // Nastavení atributů
            shape.setFillColor(ColorUtils.colorToHex(fillColor));
            shape.setBorderColor(ColorUtils.colorToHex(borderColor));
            shape.setStrokeWidth(borderWidth);
            // Nastavení tvaru
            mainWindow.getDrawPanel().setDrawnShape(shape);
            mainWindow.setEnabled(true);
            dispose();
        });

        buttonPanel.add(okButton);
        // Mezera mezi OK a Cancel buttonem
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        JButton cancelButton = new JButton("Zrušit");
        cancelButton.addActionListener(e -> {
            mainWindow.setEnabled(true);
            dispose();
        });
        buttonPanel.add(cancelButton);

        // Horizontální zarovnání tlačítek uvnitř panelu
        buttonPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        // Horizontální zarovnání panelu tlačítek v rodičovském panelu
        JPanel buttonPanelContainer = new JPanel();
        buttonPanelContainer.setLayout(new BoxLayout(buttonPanelContainer, BoxLayout.X_AXIS));
        buttonPanelContainer.add(Box.createHorizontalGlue());
        buttonPanelContainer.add(buttonPanel);
        buttonPanelContainer.add(Box.createHorizontalGlue());

        contentPane.add(buttonPanelContainer);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mainWindow.setEnabled(true);
                dispose();
            }
        });

        setContentPane(contentPane);
        if (shape.getClass() != Line.class) {
            setSize(300, 180);
        } else {
            setSize(300, 153);
        }
        setResizable(false);
        setLocationRelativeTo(mainWindow);
        setVisible(true);
    }
}
