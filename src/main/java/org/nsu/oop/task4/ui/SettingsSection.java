package org.nsu.oop.task4.ui;

import org.nsu.oop.task4.controller.Controller;
import org.nsu.oop.task4.controller.EventChangeSpeed;
import org.nsu.oop.task4.factory.parts.Accessory;
import org.nsu.oop.task4.factory.parts.Engine;
import org.nsu.oop.task4.factory.parts.Frame;
import org.nsu.oop.task4.pubsub.Publisher;

import javax.swing.*;

public class SettingsSection extends JPanel implements Publisher<EventChangeSpeed> {
    private final Controller controller = Controller.getInstance();

    public SettingsSection() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        addSlider("Frame Production Speed", Frame.class, 0, 100);
        addSlider("Engine Production Speed", Engine.class, 0, 100);
        addSlider("Accessory Production Speed", Accessory.class, 0, 100);
    }

    private void addSlider(String title, Class<?> partClass, int beg, int end) {
        JSlider slider = new JSlider(beg, end);

        slider.addChangeListener(e -> {
            JSlider slider1 = (JSlider) e.getSource();
            int speed = slider1.getValue();
            publish(new EventChangeSpeed(partClass, speed));
        });

        slider.setLabelTable(slider.createStandardLabels(10));
        slider.setPaintLabels(true);
        slider.setAlignmentX(CENTER_ALIGNMENT);

        JLabel label = new JLabel(title);
        label.setAlignmentX(CENTER_ALIGNMENT);

        add(label);
        add(slider);
    }

    @Override
    public void publish(EventChangeSpeed event) {
        controller.onEvent(event);
    }
}
