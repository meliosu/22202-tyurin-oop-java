package org.nsu.oop.task4.factory.parts;

public abstract class CarPart {
    private final int id;

    public CarPart(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
