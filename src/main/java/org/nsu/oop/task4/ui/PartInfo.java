package org.nsu.oop.task4.ui;

import javax.swing.*;
import java.awt.*;

public class PartInfo extends JPanel {
    private static final String fontName = "Arial";
    private static final int fontSize = 14;
    private static final int titleFontSize = 18;

    private final JLabel current;
    private final JLabel total;

    public PartInfo(String title) {
        super(new GridLayout(3, 1));;

        current = new JLabel();
        total = new JLabel();
        JLabel titleLabel = new JLabel(title);

        Font boldFont = new Font(fontName, Font.BOLD, titleFontSize);
        titleLabel.setFont(boldFont);

        Font italicFont = new Font(fontName, Font.ITALIC, fontSize);
        current.setFont(italicFont);
        total.setFont(italicFont);

        add(titleLabel);
        add(current);
        add(total);
    }

    public void setAmounts(int currentAmount, int totalAmount) {
        SwingUtilities.invokeLater(() -> {
            current.setText("Current stock: " + currentAmount);
            total.setText("Total: " + totalAmount);
        });
    }
}
