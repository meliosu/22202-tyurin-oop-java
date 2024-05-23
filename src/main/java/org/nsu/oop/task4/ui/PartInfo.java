package org.nsu.oop.task4.ui;

import javax.swing.*;

public class PartInfo extends JPanel {
    private final JLabel current;
    private final JLabel total;

    public PartInfo() {
        super();

        current = new JLabel();
        total = new JLabel();

        add(new JLabel("current: " + current + ", total: " + total));
    }

    public void setAmounts(int currentAmount, int totalAmount) {
        SwingUtilities.invokeLater(() -> {
            current.setText(String.valueOf(currentAmount));
            total.setText(String.valueOf(totalAmount));
        });
    }
}
