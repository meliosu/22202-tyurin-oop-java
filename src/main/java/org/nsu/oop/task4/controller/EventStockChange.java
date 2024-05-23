package org.nsu.oop.task4.controller;

public class EventStockChange extends FactoryEvent {
    private final Class<?> partClass;
    private final int amount;

    public EventStockChange(Class<?> partClass, int amount) {
        this.partClass = partClass;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public Class<?> getPartClass() {
        return partClass;
    }
}
