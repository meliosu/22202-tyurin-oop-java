package org.nsu.oop.task4.ui;

import javax.swing.*;

public class SettingsSection extends JPanel {
    public SettingsSection() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        addSlider("Frame Production Speed", 0, 100);
        addSlider("Engine Production Speed", 0, 100);
        addSlider("Accessory Production Speed", 0, 100);
    }

    private void addSlider(String title, int beg, int end) {
        JSlider slider = new JSlider(beg, end);
        slider.setLabelTable(slider.createStandardLabels(10));
        slider.setPaintLabels(true);
        slider.setAlignmentX(CENTER_ALIGNMENT);

        JLabel label = new JLabel(title);
        label.setAlignmentX(CENTER_ALIGNMENT);

        add(label);
        add(slider);
    }
}
