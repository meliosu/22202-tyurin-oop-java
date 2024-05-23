package org.nsu.oop.task4.controller;

import org.nsu.oop.task4.factory.Factory;
import org.nsu.oop.task4.factory.parts.Accessory;
import org.nsu.oop.task4.factory.parts.Engine;
import org.nsu.oop.task4.factory.parts.Frame;
import org.nsu.oop.task4.pubsub.Publisher;
import org.nsu.oop.task4.pubsub.Subscriber;
import org.nsu.oop.task4.ui.Menu;

public class Controller implements Subscriber<FactoryEvent>, Publisher<FactoryEvent> {
    Factory factory;
    Menu menu;

    public Controller(Factory factory, Menu menu) {
        this.factory = factory;
        this.menu = menu;
    }

    @Override
    public void onEvent(FactoryEvent event) {
        if (event instanceof EventChangeSpeed) {
            handleSpeedChange((EventChangeSpeed) event);
        } else if (event instanceof EventStockChange) {
            handleStockChange((EventStockChange) event);
        }
    }

    @Override
    public void publish(FactoryEvent event) {
        menu.onEvent((EventStockChange) event);
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
        this.publish(event);
    }
}
