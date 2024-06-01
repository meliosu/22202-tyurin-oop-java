package org.nsu.oop.task4.ui;

import org.nsu.oop.task4.controller.EventStockChange;
import org.nsu.oop.task4.factory.parts.Accessory;
import org.nsu.oop.task4.factory.parts.Car;
import org.nsu.oop.task4.factory.parts.Engine;
import org.nsu.oop.task4.factory.parts.Frame;
import org.nsu.oop.task4.pubsub.Subscriber;

import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame implements Subscriber<EventStockChange> {
    private static final int height = 480;
    private static final int width = 640;

    private final StatsSection statsSection;

    public Menu() {
        super();

        this.setTitle("MT Factory Simulation");
        this.setMinimumSize(new Dimension(width, height));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = getContentPane();

        statsSection = new StatsSection();
        container.add(statsSection, BorderLayout.NORTH);

        SettingsSection settingsSection = new SettingsSection();
        container.add(settingsSection, BorderLayout.CENTER);

        this.pack();
    }

    public void display() {
        this.setVisible(true);
    }

    @Override
    public void onEvent(EventStockChange event) {
        Class<?> partClass = event.getPartClass();
        int current = event.getCurrentAmount();
        int total = event.getTotalAmount();

        if (partClass == Frame.class) {
            statsSection.setFrameAmount(current, total);
        } else if (partClass == Engine.class) {
            statsSection.setEngineAmount(current, total);
        } else if (partClass == Accessory.class) {
            statsSection.setAccessoryAmount(current, total);
        } else if (partClass == Car.class) {
            statsSection.setCarAmount(current, total);
        }
    }
}
