package org.nsu.oop.task4.factory;

import java.util.ArrayList;
import java.util.List;

public abstract class CarPartStorage {
    private final int capacity;
    private List<CarPart> parts;

    public CarPartStorage(int capacity) {
        this.capacity = capacity;
        parts = new ArrayList<>();
    }

    public void loadPart(CarPart part) {
        parts.add(part);
    }

    public CarPart takePart() {
        return parts.removeLast();
    }

    public int getCapacity() {
        return capacity;
    }
}
