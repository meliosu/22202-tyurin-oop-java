package org.nsu.oop.task4.ui;

import org.nsu.oop.task4.controller.Controller;
import org.nsu.oop.task4.controller.EventChangeSpeed;
import org.nsu.oop.task4.factory.parts.Accessory;
import org.nsu.oop.task4.factory.parts.Engine;
import org.nsu.oop.task4.factory.parts.Frame;
import org.nsu.oop.task4.pubsub.Publisher;

import javax.swing.*;
import java.awt.*;

public class SettingsSection extends JPanel implements Publisher<EventChangeSpeed> {
    private final Controller controller = Controller.getInstance();
    private static final int min = 10;
    private static final int max = 100;
    private static final int numLabels = 9;

    public SettingsSection() {
        super(new GridLayout(3, 1));

        addSlider("Frame Production Speed", Frame.class);
        addSlider("Engine Production Speed", Engine.class);
        addSlider("Accessory Production Speed", Accessory.class);
    }

    private void addSlider(String title, Class<?> partClass) {
        JSlider slider = new JSlider(SettingsSection.min, SettingsSection.max);

        slider.addChangeListener(e -> {
            JSlider slider1 = (JSlider) e.getSource();
            int speed = slider1.getValue();
            int sleepMs = 500 / (speed + 1);
            publish(new EventChangeSpeed(partClass, sleepMs));
        });

        slider.setLabelTable(slider.createStandardLabels(
                (SettingsSection.max - SettingsSection.min) / numLabels));

        slider.setPaintLabels(true);
        slider.setAlignmentX(CENTER_ALIGNMENT);

        JLabel label = new JLabel(title);
        label.setAlignmentX(CENTER_ALIGNMENT);

        JPanel wrapper = new JPanel();
        wrapper.setLayout(new GridLayout(2, 1));

        wrapper.add(label);
        wrapper.add(slider);

        add(wrapper);
    }

    @Override
    public void publish(EventChangeSpeed event) {
        controller.onEvent(event);
    }
}
