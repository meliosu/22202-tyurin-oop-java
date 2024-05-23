package org.nsu.oop.task4.factory.storage;

import org.nsu.oop.task4.factory.parts.CarPart;

import java.util.ArrayList;
import java.util.List;

public abstract class CarPartStorage {
    private final int capacity;
    private final List<CarPart> parts;

    public CarPartStorage(int capacity) {
        this.capacity = capacity;
        parts = new ArrayList<>();
    }

    public synchronized void loadPart(CarPart part) throws InterruptedException {
        while (isFull()) {
            wait();
        }

        parts.add(part);
        notifyAll();
    }

    public synchronized CarPart takePart() throws InterruptedException {
        while (isEmpty()) {
            wait();
        }

        CarPart part = parts.remove(0);
        notifyAll();
        return part;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSize() {
        return parts.size();
    }

    public boolean isEmpty() {
        return parts.isEmpty();
    }

    public boolean isFull() {
        return parts.size() == capacity;
    }
}
