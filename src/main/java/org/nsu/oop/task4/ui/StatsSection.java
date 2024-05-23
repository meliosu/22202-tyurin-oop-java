package org.nsu.oop.task4.ui;

import javax.swing.*;
import java.awt.*;

public class StatsSection extends JPanel {
    private final PartInfo framePanel;
    private final PartInfo enginePanel;
    private final PartInfo accessoryPanel;

    public StatsSection() {
        super();

        framePanel = new PartInfo();
        enginePanel = new PartInfo();
        accessoryPanel = new PartInfo();

        add(framePanel);
        add(enginePanel);
        add(accessoryPanel);
    }

    public void setFrameAmount(int currentAmount, int totalAmount) {
        framePanel.setAmounts(currentAmount, totalAmount);
    }

    public void setEngineAmount(int currentAmount, int totalAmount) {
        enginePanel.setAmounts(currentAmount, totalAmount);
    }

    public void setAccessoryAmount(int currentAmount, int totalAmount) {
        accessoryPanel.setAmounts(currentAmount, totalAmount);
    }
}
