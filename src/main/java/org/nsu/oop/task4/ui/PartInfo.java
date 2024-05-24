package org.nsu.oop.task4.ui;

import javax.swing.*;
import java.awt.*;

public class PartInfo extends JPanel {
    private final JLabel current;
    private final JLabel total;

    public PartInfo(String title) {
        super(new GridLayout(3, 1));

        current = new JLabel();
        total = new JLabel();

        add(new JLabel(title));
        add(current);
        add(total);
    }

    public void setAmounts(int currentAmount, int totalAmount) {
        SwingUtilities.invokeLater(() -> {
            current.setText("Current stock: " + currentAmount);
            total.setText("Total stock: " + totalAmount);
        });
    }
}
