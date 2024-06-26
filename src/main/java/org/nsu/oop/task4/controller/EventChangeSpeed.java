package org.nsu.oop.task4.controller;


public class EventChangeSpeed extends FactoryEvent {
    private final Class<?> partClass;
    private final int speed;

    public EventChangeSpeed(Class<?> partClass, int speed) {
        this.partClass = partClass;
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public Class<?> getPartClass() {
        return partClass;
    }
}
