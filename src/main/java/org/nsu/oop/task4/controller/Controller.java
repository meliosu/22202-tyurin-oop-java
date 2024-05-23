package org.nsu.oop.task4.controller;

import org.nsu.oop.task4.factory.Factory;
import org.nsu.oop.task4.factory.parts.Accessory;
import org.nsu.oop.task4.factory.parts.Engine;
import org.nsu.oop.task4.factory.parts.Frame;
import org.nsu.oop.task4.pubsub.Event;
import org.nsu.oop.task4.pubsub.Publisher;
import org.nsu.oop.task4.pubsub.Subscriber;
import org.nsu.oop.task4.ui.Menu;

public class Controller implements Subscriber<FactoryEvent> {
    Factory factory;
    Menu menu;

    public Controller(Factory factory) {
        this.factory = factory;
    }

    @Override
    public void onEvent(FactoryEvent event) {
        if (event instanceof EventChangeSpeed) {
            handleSpeedChange((EventChangeSpeed) event);
        } else if (event instanceof EventStockChange) {
            handleStockChange((EventStockChange) event);
        }
    }

    private void handleSpeedChange(EventChangeSpeed event) {
        Class<?> partClass = event.getPartClass();
        int speed = event.getSpeed();

        if (partClass == Frame.class) {
            factory.setFrameSpeed(speed);
        } else if (partClass == Engine.class) {
            factory.setEngineSpeed(speed);
        } else if (partClass == Accessory.class) {
            factory.setAccessorySpeed(speed);
        }
    }

    private void handleStockChange(EventStockChange event) {
        Class<?> partClass = event.getPartClass();
        int amount = event.getAmount();

        if (partClass == Frame.class) {
            menu.setFrameAmount(amount);
        } else if (partClass == Engine.class) {
            menu.setEngineAmount(amount);
        } else if (partClass == Accessory.class) {
            menu.setAccessoryAmount(amount);
        }
    }
}
