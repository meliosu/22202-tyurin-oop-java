package org.nsu.oop.task4.ui;

import javax.swing.*;
import java.awt.*;

public class StatsSection extends JPanel {
    private final PartInfo framePanel;
    private final PartInfo enginePanel;
    private final PartInfo accessoryPanel;
    private final PartInfo carPanel;

    public StatsSection() {
        super(new GridLayout(2, 2));

        framePanel = new PartInfo("Frame Production");
        enginePanel = new PartInfo("Engine Production");
        accessoryPanel = new PartInfo("Accessory Production");
        carPanel = new PartInfo("Car Production");

        add(framePanel);
        add(enginePanel);
        add(accessoryPanel);
        add(carPanel);
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

    public void setCarAmount(int currentAmount, int totalAmount) {
        carPanel.setAmounts(currentAmount, totalAmount);
    }
}
