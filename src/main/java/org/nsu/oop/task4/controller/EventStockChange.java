package org.nsu.oop.task4.controller;

public class EventStockChange extends FactoryEvent {
    private final Class<?> partClass;
    private final int current;
    private final int total;

    public EventStockChange(Class<?> partClass, int current, int total) {
        this.partClass = partClass;
        this.current = current;
        this.total = total;
    }

    public int getTotalAmount() {
        return total;
    }

    public int getCurrentAmount() {
        return current;
    }

    public Class<?> getPartClass() {
        return partClass;
    }
}
