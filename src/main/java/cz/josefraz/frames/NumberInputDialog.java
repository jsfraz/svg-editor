package cz.josefraz.frames;

import javax.swing.*;
import javax.xml.bind.JAXBException;

import cz.josefraz.utils.*;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import cz.josefraz.shapes.Canvas;

public class NumberInputDialog extends JDialog {

    private JSlider slider;
    private JButton okButton;
    private String okButtonText;

    public NumberInputDialog(int min, int max, String title, String okButtonText, SVGEditor mainWindow) {
        super(mainWindow, title, true); // Použití mainWindow jako vlastníka dialogu

        JPanel panel = new JPanel(new BorderLayout());
        JPanel controlsPanel = new JPanel(new FlowLayout());

        slider = new JSlider(JSlider.HORIZONTAL, min, max, max);
        JLabel minLabel = new JLabel(String.valueOf(min));
        JLabel maxLabel = new JLabel(String.valueOf(max));

        slider.addChangeListener(e -> updateSelected(slider.getValue()));

        this.okButtonText = okButtonText;
        okButton = new JButton(String.format(this.okButtonText + " (%s)", slider.getValue()));
        okButton.addActionListener(e -> {
            // Vygenerování tvarů
            Singleton.getInstance()
                    .addShapes(ShapeUtils.generateRandomShapes(slider.getValue(), Singleton.getInstance().getDrawPanel().getWidth(),
                    Singleton.getInstance().getDrawPanel().getHeight()));
            // Refresh
            mainWindow.setEnabled(true);
            Singleton.getInstance().getDrawPanel().repaint();
            mainWindow.getEditSplitPane().refreshTables();
            try {
                Singleton.getInstance().getCodeArea().setText(XMLUtils.getXml(Canvas.getCanvas()));
            } catch (JAXBException e1) {
                e1.printStackTrace();
            }
            dispose();
        });

        JButton cancelButton = new JButton("Zrušit");
        cancelButton.addActionListener(e -> {
            mainWindow.setEnabled(true);
            dispose();
        });

        controlsPanel.add(minLabel);
        controlsPanel.add(slider);
        controlsPanel.add(maxLabel);
        controlsPanel.add(okButton);
        controlsPanel.add(cancelButton);

        panel.add(controlsPanel, BorderLayout.CENTER);
        add(panel);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mainWindow.setEnabled(true);
                dispose();
            }
        });

        setSize(280, 100);
        setResizable(false);
        setLocationRelativeTo(mainWindow);
        setVisible(true);
    }

    private void updateSelected(int value) {
        okButton.setText(String.format(this.okButtonText + " (%s)", slider.getValue()));
    }
}
