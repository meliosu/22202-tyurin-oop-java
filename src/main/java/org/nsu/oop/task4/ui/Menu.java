package org.nsu.oop.task4.ui;

import org.nsu.oop.task4.controller.EventStockChange;
import org.nsu.oop.task4.factory.parts.Accessory;
import org.nsu.oop.task4.factory.parts.Engine;
import org.nsu.oop.task4.factory.parts.Frame;
import org.nsu.oop.task4.pubsub.Subscriber;

import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame implements Subscriber<EventStockChange> {
    private final StatsSection statsSection;
    private final SettingsSection settingsSection;

    public Menu() {
        this.setTitle("MT Factory Simulation");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1280, 720);

        Container container = getContentPane();

        statsSection = new StatsSection();
        container.add(statsSection, TOP_ALIGNMENT);

        settingsSection = new SettingsSection();
        container.add(settingsSection, BOTTOM_ALIGNMENT);

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
        }
    }
}
